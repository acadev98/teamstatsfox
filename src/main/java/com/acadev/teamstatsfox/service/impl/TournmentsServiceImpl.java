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
import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.entity.OpponentsTournment;
import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.PlayersTournment;
import com.acadev.teamstatsfox.database.entity.Presents;
import com.acadev.teamstatsfox.database.entity.Tournments;
import com.acadev.teamstatsfox.database.repository.TournmentRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.request.TournmentDetailsRequest;
import com.acadev.teamstatsfox.model.request.TournmentRequest;
import com.acadev.teamstatsfox.model.response.PlayerStatisticsResponse;
import com.acadev.teamstatsfox.model.response.TournmentsDetailsResponse;
import com.acadev.teamstatsfox.service.CardsService;
import com.acadev.teamstatsfox.service.GoalsService;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.service.OpponentsService;
import com.acadev.teamstatsfox.service.OpponentsTournmentsService;
import com.acadev.teamstatsfox.service.PlayerService;
import com.acadev.teamstatsfox.service.PlayersTournmentService;
import com.acadev.teamstatsfox.service.PresentsService;
import com.acadev.teamstatsfox.service.TournmentsService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;
import com.acadev.teamstatsfox.utils.enums.ECardType;

@Service
public class TournmentsServiceImpl implements TournmentsService {

	@Autowired
	private TournmentRepository repository;

	@Autowired
	private PlayersTournmentService playersTournmentService;

	@Autowired
	private OpponentsTournmentsService opponentsTournmentService;

	@Autowired
	private OpponentsService opponentsService;

	@Autowired
	private MatchesService matchesService;

	@Autowired
	private PresentsService presentsService;

	@Autowired
	private PlayerService playersService;

	@Autowired
	private GoalsService goalsService;

	@Autowired
	private CardsService cardsService;

	@Autowired
	private MapperService mapperService;

	public Long getNextId() {
		Optional<Tournments> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId() + 1);
		return 1L;
	}

	public String echo() {
		return "tournment echo message";
	}

	public List<Tournments> getTournments() {

		List<Tournments> tournments = repository.findAll();
		if (tournments.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		List<Tournments> tournmentsListOrdered = tournments.stream()
				.sorted(Comparator.comparing(Tournments::getStartDate)).collect(Collectors.toList());

		return tournmentsListOrdered;
	}

	public Tournments create(Tournments tournment) {
		tournment.setId(getNextId());
		return repository.save(tournment);
	}

	public Tournments getTournmentById(Long id) {

		Optional<Tournments> tournment = repository.findById(id);
		if (tournment.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return tournment.get();
	}

	public List<PlayerStatisticsResponse> getStatisticsPlayersByTournmentId(Long id) {
		List<PlayersTournment> players = playersTournmentService.getPlayersByTournmentId(id);
		List<Matches> matches = matchesService.getMatchesByTournmentId(id);

		List<Long> matchesIds = new ArrayList<>();
		if (!matches.isEmpty()) {
			matchesIds = matches.stream().map(mapperService::getMatchesIds).collect(Collectors.toList());
		}

		List<PlayerStatisticsResponse> response = new ArrayList<>();
		for (PlayersTournment pl : players) {

			Players player = playersService.getPlayer(pl.getPlayerId());
			List<Presents> presents = presentsService.getPresentsByPlayerIdAndByMatchesIds(pl.getPlayerId(),
					matchesIds);
			List<Goals> goals = goalsService.getGoalsByPlayerIdAndByMatchesIds(pl.getPlayerId(), matchesIds);
			List<Goals> assists = goalsService.getGoalsByAssistsPlayerIdAndByMatchesIds(pl.getPlayerId(), matchesIds);
			List<Matches> captains = matches.stream().filter(m -> m.getCaptain().equals(pl.getPlayerId()))
					.collect(Collectors.toList());
			List<Cards> yellowCards = cardsService.getCardsByPlayerIdAndTypeAndByMatchesIds(pl.getPlayerId(),
					ECardType.YELLOW, matchesIds);
			List<Cards> redCards = cardsService.getCardsByPlayerIdAndTypeAndByMatchesIds(pl.getPlayerId(),
					ECardType.RED, matchesIds);

			PlayerStatisticsResponse playerStatistics = PlayerStatisticsResponse.builder().id(player.getId())
					.player(player.getLastname() + " " + player.getName()).matches(presents.size()).goals(goals.size())
					.assists(assists.size()).captains(captains.size()).yellowCards(yellowCards.size())
					.redCards(redCards.size()).build();

			response.add(playerStatistics);

		}
		return response;
	}

	public TournmentsDetailsResponse getTournmentsDetailsById(Long id) {

		Tournments tournment = getTournmentById(id);
		List<PlayersTournment> playersTournments = playersTournmentService.getPlayersByTournmentId(id);
		List<OpponentsTournment> opponentsTournments = opponentsTournmentService.getOpponentsByTournmentId(id);
		List<Matches> matches = matchesService.getMatchesByTournmentId(id);

		List<Players> players = new ArrayList<>();
		if (!playersTournments.isEmpty()) {
			List<Long> playersIds = playersTournments.stream().map(mapperService::getPlayerIds)
					.collect(Collectors.toList());

			for (Long playerId : playersIds) {
				players.add(playersService.getPlayer(playerId));
			}
		}

		List<Opponents> opponents = new ArrayList<>();
		if (!opponentsTournments.isEmpty()) {
			List<Long> opponentsIds = opponentsTournments.stream().map(mapperService::getOpponentsIds)
					.collect(Collectors.toList());

			for (Long opponentId : opponentsIds) {
				opponents.add(opponentsService.getOpponentById(opponentId));
			}
		}

		TournmentsDetailsResponse response = TournmentsDetailsResponse.builder().tournment(tournment).players(players)
				.opponents(opponents).matches(matches).build();

		return response;
	}

	public List<TournmentsDetailsResponse> getTournmentsDetails() {
		List<Tournments> tournments = getTournments();
		List<TournmentsDetailsResponse> tournmentsResponseList = new ArrayList<>();

		for (Tournments t : tournments) {
			List<OpponentsTournment> opponentsTournments = opponentsTournmentService
					.getOpponentsByTournmentId(t.getId());
			List<Opponents> opponents = new ArrayList<>();
			if (!opponentsTournments.isEmpty()) {
				List<Long> opponentsIds = opponentsTournments.stream().map(mapperService::getOpponentsIds)
						.collect(Collectors.toList());

				for (Long opponentId : opponentsIds) {
					opponents.add(opponentsService.getOpponentById(opponentId));
				}
			}

			TournmentsDetailsResponse tournmentsResponse = TournmentsDetailsResponse.builder().tournment(t)
					.opponents(opponents).build();

			tournmentsResponseList.add(tournmentsResponse);

		}

		return tournmentsResponseList;

	}

	public TournmentsDetailsResponse create(TournmentDetailsRequest tournmentDetails) {

		TournmentRequest tournmentsRequest = tournmentDetails.getTournment();
		List<Players> playersRequest = tournmentDetails.getPlayers();
		List<Opponents> opponentsRequest = tournmentDetails.getOpponents();

		Tournments tournmentEntity = Tournments.builder().startDate(tournmentsRequest.getDate())
				.name(tournmentsRequest.getName()).description(tournmentsRequest.getDescription())
				.active(tournmentsRequest.getActive()).build();

		Tournments tournmentCreated = create(tournmentEntity);

		for (Players pr : playersRequest) {
			PlayersTournment playerTournmentEntity = PlayersTournment.builder().playerId(pr.getId())
					.tournmentId(tournmentCreated.getId()).build();
			playersTournmentService.create(playerTournmentEntity);
		}

		for (Opponents op : opponentsRequest) {
			OpponentsTournment opponentTournmentEntity = OpponentsTournment.builder().opponentId(op.getId())
					.tournmentId(tournmentCreated.getId()).build();
			opponentsTournmentService.create(opponentTournmentEntity);
		}

		return getTournmentsDetailsById(tournmentCreated.getId());
	}

	public Tournments delete(Long id) {

		Tournments tournment = getTournmentById(id);
		repository.delete(tournment);

		playersTournmentService.deleteByTournmentId(id);
		opponentsTournmentService.deleteByTournmentId(id);

		return tournment;
	}

	public TournmentsDetailsResponse update(Long id, TournmentDetailsRequest tournmentDetails) {

		Tournments tournmentEntity = getTournmentById(id);
		TournmentRequest tournmentUpdate = tournmentDetails.getTournment();

		tournmentEntity.setName(tournmentUpdate.getName());
		tournmentEntity.setDescription(tournmentUpdate.getDescription());
		tournmentEntity.setStartDate(tournmentUpdate.getDate());
		tournmentEntity.setActive(tournmentUpdate.getActive());

		repository.save(tournmentEntity);

		// elimino jugadores del torneo y cargo la nueva lista
		playersTournmentService.deleteByTournmentId(id);
		for (Players pr : tournmentDetails.getPlayers()) {
			PlayersTournment playerTournmentEntity = PlayersTournment.builder().playerId(pr.getId()).tournmentId(id)
					.build();
			playersTournmentService.create(playerTournmentEntity);
		}

		// elimino rivales del torneo y cargo la nueva lista
		opponentsTournmentService.deleteByTournmentId(id);
		for (Opponents op : tournmentDetails.getOpponents()) {
			OpponentsTournment opponentTournmentEntity = OpponentsTournment.builder().opponentId(op.getId())
					.tournmentId(id).build();
			opponentsTournmentService.create(opponentTournmentEntity);
		}

		return getTournmentsDetailsById(id);
	}

}
