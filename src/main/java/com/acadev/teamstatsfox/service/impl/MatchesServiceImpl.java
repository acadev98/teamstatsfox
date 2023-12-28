package com.acadev.teamstatsfox.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.acadev.teamstatsfox.database.repository.MatchesRepository;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.request.CardRequest;
import com.acadev.teamstatsfox.model.request.GoalRequest;
import com.acadev.teamstatsfox.model.request.MatchDetailsRequest;
import com.acadev.teamstatsfox.model.request.MatchRequest;
import com.acadev.teamstatsfox.model.request.PresentRequest;
import com.acadev.teamstatsfox.model.response.MatchesDetailsResponse;
import com.acadev.teamstatsfox.service.CardsService;
import com.acadev.teamstatsfox.service.GoalsService;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.service.PresentsService;
import com.acadev.teamstatsfox.utils.FunctionsUtils;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class MatchesServiceImpl implements MatchesService {

	@Autowired
	private MatchesRepository repository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GoalsService goalsService;

	@Autowired
	private PresentsService presentsService;

	@Autowired
	private CardsService cardsService;

	@Autowired
	private MapperService mapperService;

	public Long getNextId() {
		Optional<Matches> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId()+1);
		return 1L;
	}

	public String echo() {
		return "matches echo message";
	}

	public List<Matches> getMatches() {

		List<Matches> matches = (List<Matches>) repository.findAll();
		if (matches.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return matches;
	}

	public MatchesDetailsResponse create(MatchDetailsRequest matchDetails) {

		MatchRequest matchRequest = matchDetails.getMatch();
		List<GoalRequest> goalsRequest = matchDetails.getGoals();
		List<CardRequest> cardsRequest = matchDetails.getCards();
		List<PresentRequest> presentsRequest = matchDetails.getPresents();

		LocalDateTime localDateTimeMatch = FunctionsUtils.generateLocalDateTimeFromLocalDateAndTimeString(matchRequest.getDate(), matchRequest.getTime());
		Integer ourGoals = FunctionsUtils.calculateOurGoals(goalsRequest);
		Integer rivalsGoals = (goalsRequest.size()-ourGoals);

		Matches matchEntity = Matches.builder()
			.datetime(localDateTimeMatch)
			.opponent(matchRequest.getOpponent())
			.description(matchRequest.getResume())
			.competition(matchRequest.getTournment())
			.ourGoals(ourGoals)
			.captain(matchDetails.getCaptain()==null?0:matchDetails.getCaptain())
			.rivalGoals(rivalsGoals)
			.build();
		
		Matches matchCreated = create(matchEntity);
		
		for (PresentRequest pr : presentsRequest) {
			Presents presentEntity = Presents.builder().matchId(matchCreated.getId()).playerId(pr.getId()).build();
			presentsService.create(presentEntity);
		}
		
		for (GoalRequest gl : goalsRequest) {
			Goals goalsEntity = Goals.builder()
					.matchId(matchCreated.getId())
					.playerId(gl.getPlayerId().equals("")?0:Long.parseLong(gl.getPlayerId()))
					.assistPlayerId(gl.getAssistPlayerId().equals("")?0:Long.parseLong(gl.getAssistPlayerId()))
					.type(gl.getType())
					.minute(gl.getMinute().equals("")?0:Integer.parseInt(gl.getMinute()))
					.our(gl.getOur())
					.build();
			goalsService.create(goalsEntity);
		}
		
		for (CardRequest crd : cardsRequest) {
			Cards cardsEntity = Cards.builder()
					.matchId(matchCreated.getId())
					.playerId(Long.parseLong(crd.getPlayerId()))
					.type(crd.getType())
					.minute(crd.getMinute().equals("")?0:Integer.parseInt(crd.getMinute()))
					.build();
			cardsService.create(cardsEntity);
		}

		return getMatchDetails(matchCreated.getId());
		
	}

	public Matches create(Matches matches) {
		matches.setId(getNextId());
		return repository.save(matches);
	}

	public Matches getMatch(Long id) {

		Optional<Matches> match = repository.findById(id);
		if (match.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return match.get();
	}

	public MatchesDetailsResponse getMatchDetails(Long id) {

		List<Goals> goals = goalsService.getGoalsByMatchId(id);
		List<Presents> presents = presentsService.getPresentsByMatchId(id);
		List<Cards> cards = cardsService.getCardsByMatchId(id);
		List<Players> playersPresents = new ArrayList<>();

		if (!presents.isEmpty()) {
			List<Long> playersIdsPresents= presents.stream().map(mapperService::getPlayerIds).collect(Collectors.toList());

			for (Long playerId : playersIdsPresents) {
				playersPresents.add(playerRepository.findById(playerId).get());
			}
		}

		MatchesDetailsResponse matchDetails = MatchesDetailsResponse.builder()
				.match(getMatch(id))
				.goals(goals)
				.players(playersPresents)
				.cards(cards)
				.build();

		return matchDetails;
	}

	public Matches delete(Long id) {
		
		Optional<Matches> match = repository.findById(id);

		if (match.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		repository.delete(match.get());

		goalsService.deleteByMatchId(id);
		cardsService.deleteByMatchId(id);
		presentsService.deleteByMatchId(id);
		
		return match.get();
	}

}
