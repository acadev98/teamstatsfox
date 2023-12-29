package com.acadev.teamstatsfox.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Cards;
import com.acadev.teamstatsfox.database.entity.Goals;
import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.Presents;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.request.PlayerRequest;
import com.acadev.teamstatsfox.model.response.PlayersPlayersDetailsResponse;
import com.acadev.teamstatsfox.model.response.PrevAndNextPlayersResponse;
import com.acadev.teamstatsfox.service.CardsService;
import com.acadev.teamstatsfox.service.GoalsService;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.service.PlayerService;
import com.acadev.teamstatsfox.service.PresentsService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class PlayerServiceImpl implements PlayerService {
	
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
				return (entityMaxId.get().getId()+1);
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

	public PlayersPlayersDetailsResponse getPlayerDetails(Long id) {

		Optional<Players> player = repository.findById(id);
		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		List<Goals> goals = goalsService.getGoalsByPlayerId(id);
		List<Goals> assists = goalsService.getAssistsByPlayerId(id);
		List<Cards> cards = cardsService.getCardsPlayerId(id);
		List<Presents> presents = presentsService.getPresentsByPlayerId(id);
		
		List<Matches> matches = new ArrayList<>();

		if (!presents.isEmpty()) {
			for (Presents present: presents) {
				matches.add(matchesService.getMatch(present.getMatchId()));
			}
		}
		
		PlayersPlayersDetailsResponse playerDetails = PlayersPlayersDetailsResponse.builder()
				.player(player.get())
				.goals(goals)
				.cards(cards)
				.assists(assists)
				.matches(matches)
				.build();
				
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

	public Players update(Long id, PlayerRequest request) {
		Optional<Players> player = repository.findById(id);

		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		Players playerUpdated = player.get();
		playerUpdated.setDni(request.getDni());
		playerUpdated.setLastname(request.getLastname().toUpperCase());
		playerUpdated.setName(request.getName().toUpperCase());
		playerUpdated.setPosition(request.getPosition().toUpperCase());
		playerUpdated.setSecondPosition(request.getSecondPosition().toUpperCase());
		playerUpdated.setBirthday(request.getBirthday());
		playerUpdated.setPlayingSince(request.getPlayingSince());

		repository.save(playerUpdated);

		return repository.save(playerUpdated);
	}

	public List<Integer> findNumbers() {
		List<Integer> numbersPlayers = repository.findNumbers();
		
		if (numbersPlayers.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);
		
		return numbersPlayers;
	}

	public PrevAndNextPlayersResponse getPlayerPrevAndNext(Long id) {

		List<Players> playersList = getPlayersActives();
		
		List<Players> playersListOrdered = playersList.stream()
				  .sorted(Comparator.comparing(Players::getNumber))
				  .collect(Collectors.toList());
		
		Players player = getPlayer(id);

		Integer index = playersListOrdered.indexOf(player);
		Integer lastIndex = playersListOrdered.size()-1;
		
		Integer indexPrev = (index==0?lastIndex:index-1); 
		Integer indexNext = (index==lastIndex?0:index+1); 
		
		Players prev = playersListOrdered.get(indexPrev);
		Players next = playersListOrdered.get(indexNext);
		
		return PrevAndNextPlayersResponse.builder().prev(prev).next(next).build();
	}

}
