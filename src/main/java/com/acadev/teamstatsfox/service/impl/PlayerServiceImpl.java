package com.acadev.teamstatsfox.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.database.entity.Cards;
import com.acadev.teamstatsfox.database.entity.Goals;
import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.Presents;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.request.PlayerRequest;
import com.acadev.teamstatsfox.model.response.PlayerStatisticsResponse;
import com.acadev.teamstatsfox.model.response.PlayersDetailsResponse;
import com.acadev.teamstatsfox.model.response.PrevAndNextPlayersResponse;
import com.acadev.teamstatsfox.service.CardsService;
import com.acadev.teamstatsfox.service.GoalsService;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.service.PlayerService;
import com.acadev.teamstatsfox.service.PresentsService;
import com.acadev.teamstatsfox.utils.ConstantsUtils;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;
import com.acadev.teamstatsfox.utils.enums.ECardType;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Value("${path.images.player}")
	private String pathImagesPlayers;

	@Autowired
	private GoalsService goalsService;

	@Autowired
	private CardsService cardsService;

	@Autowired
	private PresentsService presentsService;

	@Autowired
	private MatchesService matchesService;

	@Autowired
	private PlayerRepository repository;

	public Long getNextId() {
		Optional<Players> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId() + 1);
		return 1L;
	}

	public Players create(PlayerRequest request) {
		Players player = Players.builder().id(getNextId()).dni(request.getDni())
				.lastname(request.getLastname().toUpperCase()).name(request.getName().toUpperCase())
				.position(request.getPosition().toUpperCase())
				.number(request.getNumber() != null ? request.getNumber() : null)
				.secondPosition(request.getSecondPosition() != null ? request.getSecondPosition().toUpperCase() : null)
				.active(request.getActive()).birthday(request.getBirthday()).playingSince(request.getPlayingSince())
				.build();

		return repository.save(player);
	}

	public Players delete(Long id) {
		Optional<Players> player = repository.findById(id);

		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		repository.delete(player.get());
		return player.get();
	}

	public String echo() {
		return "player echo message";
	}

	public Players getPlayer(Long id) {

		Optional<Players> player = repository.findById(id);
		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return player.get();
	}

	public PlayersDetailsResponse getPlayerDetails(Long id) {

		Optional<Players> player = repository.findById(id);
		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		List<Goals> goals = goalsService.getGoalsByPlayerId(id);
		List<Goals> assists = goalsService.getAssistsByPlayerId(id);
		List<Cards> cards = cardsService.getCardsPlayerId(id);
		List<Presents> presents = presentsService.getPresentsByPlayerId(id);

		List<Matches> matches = new ArrayList<>();
		if (!presents.isEmpty()) {
			for (Presents present : presents) {
				matches.add(matchesService.getMatch(present.getMatchId()));
			}
		}

		Matches firstMatch = matches.stream().sorted(Comparator.comparing(Matches::getDatetime))
				.collect(Collectors.toList()).get(0);

		player.get().setPlayingSince(firstMatch.getDatetime().toLocalDate());

		PlayersDetailsResponse playerDetails = PlayersDetailsResponse.builder().player(player.get()).goals(goals)
				.cards(cards).assists(assists).matches(matches).build();

		return playerDetails;
	}

	public List<Players> getPlayers() {

		List<Players> players = (List<Players>) repository.findAll();
		if (players.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return players;
	}

	public List<Players> getPlayersActives() {

		List<Players> players = (List<Players>) repository.findByActiveTrue();
		if (players.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return players;
	}

	public Players update(Long id, PlayerRequest player) {

		Players playerEntity = getPlayer(id);

		playerEntity.setDni(player.getDni());
		playerEntity.setName(player.getName().toUpperCase());
		playerEntity.setLastname(player.getLastname().toUpperCase());
		playerEntity.setNumber(player.getNumber() != null ? player.getNumber() : null);
		playerEntity.setPosition(player.getPosition());
		playerEntity.setSecondPosition(
				player.getSecondPosition() != null ? player.getSecondPosition().toUpperCase() : null);
		playerEntity.setActive(player.getActive());
		playerEntity.setPlayingSince(player.getPlayingSince());
		playerEntity.setBirthday(player.getBirthday());

		return repository.save(playerEntity);
	}

	public List<Integer> findNumbers() {
		List<Integer> numbersPlayers = repository.findNumbers();

		if (numbersPlayers.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return numbersPlayers;
	}

	public PrevAndNextPlayersResponse getPlayerPrevAndNext(Long id) {

		List<Players> playersList = getPlayers();

		List<Players> playersListOrdered = playersList.stream().sorted(Comparator.comparing(Players::getNumber))
				.collect(Collectors.toList());

		Players player = getPlayer(id);

		Integer index = playersListOrdered.indexOf(player);
		Integer lastIndex = playersListOrdered.size() - 1;

		Integer indexPrev = (index == 0 ? lastIndex : index - 1);
		Integer indexNext = (index == lastIndex ? 0 : index + 1);

		Players prev = playersListOrdered.get(indexPrev);
		Players next = playersListOrdered.get(indexNext);

		return PrevAndNextPlayersResponse.builder().prev(prev).next(next).build();
	}

	public List<PlayerStatisticsResponse> getPlayersStatistics() {
		List<Players> players = getPlayers();
		List<PlayerStatisticsResponse> response = new ArrayList<>();
		for (Players pl : players) {

			List<Presents> presents = presentsService.getPresentsByPlayerId(pl.getId());
			List<Goals> goals = goalsService.getGoalsByPlayerId(pl.getId());
			List<Goals> assists = goalsService.getAssistsByPlayerId(pl.getId());
			List<Matches> captains = matchesService.getMatchesByCaptain(pl.getId());
			List<Cards> yellowCards = cardsService.getCardsByPlayerIdAndType(pl.getId(), ECardType.YELLOW);
			List<Cards> redCards = cardsService.getCardsByPlayerIdAndType(pl.getId(), ECardType.RED);

			PlayerStatisticsResponse playerStatistics = PlayerStatisticsResponse.builder().id(pl.getId())
					.player(pl.getLastname() + " " + pl.getName()).matches(presents.size()).goals(goals.size())
					.assists(assists.size()).captains(captains.size()).yellowCards(yellowCards.size())
					.redCards(redCards.size()).active(pl.getActive()).build();

			response.add(playerStatistics);

		}
		return response;
	}

	public List<Integer> availableNumbers() {

		List<Integer> numbersPlayers = this.findNumbers();
		List<Integer> availableNumbers = new ArrayList<>();

		for (int i = 1; i < 100; i++) {
			if (!numbersPlayers.contains(i)) {
				availableNumbers.add(i);
			}
		}

		return availableNumbers;
	}

	public Boolean saveImage(Long id, MultipartFile file) {

		try {

			System.out.println("saveImage.");
			if (file.isEmpty()) {
				System.out.println("file.isEmpty().");
				throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);
			}

			String imageName = id + ".jpg";
			System.out.println("imageName." + imageName);

			// Crea el directorio si no existe
			File directory = new File(pathImagesPlayers);
			System.out.println("directory.");
			System.out.println("pathImagesPlayers." + pathImagesPlayers);
			if (!directory.exists()) {
				System.out.println("!directory.exists().");
				// Intentar crear la carpeta
				boolean created = directory.mkdirs();

				// Verificar si la carpeta se creó correctamente
				if (created) {
					System.out.println("Carpeta creada exitosamente");
				} else {
					System.out.println("Error al crear la carpeta");
				}
				System.out.println("directory.mkdirs().");
			}

			// Guarda la imagen en el directorio
			File imageFile = new File(pathImagesPlayers + imageName);
			FileOutputStream fos = new FileOutputStream(imageFile);
			System.out.println("imageFile.");
			fos.write(file.getBytes());
			System.out.println("fos.write(file.getBytes()).");

		} catch (IOException e) {
			System.out.println("saveImage catch.");
			e.printStackTrace();
			throw new ApiException(ApiMessage.E5XX_GENERIC_ERROR_MESSAGE);
		}
		System.out.println("saveImage return true.");

		return true;
	}

	public Resource getImageByPlayerId(Long id) {

		try {

			System.out.println("getImageByPlayerId.");
			System.out.println("pathImagesPlayers." + pathImagesPlayers);
			System.out.println("id." + id);
			Path imagePath = Paths.get(pathImagesPlayers).resolve(id.toString());
			Resource resource = new UrlResource(imagePath.toUri());

			Path imagePathNF = Paths.get(pathImagesPlayers).resolve(ConstantsUtils.IMAGE_NOT_FOUND);
			Resource resourceNF = new UrlResource(imagePathNF.toUri());

			if (resource.exists() && resource.isReadable()) {
				System.out.println("resource.exists() && resource.isReadable().");
				System.out.println("resource.exists()." + resource.exists());
				System.out.println("resource.isReadable()." + resource.isReadable());
				return resource;
			} else {
				System.out.println("no encontro resources");
				if (resourceNF.exists() && resourceNF.isReadable()) {
					System.out.println("resourceNF.exists() && resourceNF.isReadable().");
					System.out.println("resourceNF.exists()." + resourceNF.exists());
					System.out.println("resourceNF.isReadable()." + resourceNF.isReadable());
					return resourceNF;
				} else {
					System.out.println("no encontro resourceNF");
					throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);
				}
			}

		} catch (MalformedURLException e) {
			System.out.println("getImageByPlayerId catch.");
			e.printStackTrace();
			throw new ApiException(ApiMessage.E5XX_GENERIC_ERROR_MESSAGE);
		}

	}

}
