package com.acadev.teamstatsfox.service.impl;

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
import com.acadev.teamstatsfox.model.request.MatchDetailsRequest;
import com.acadev.teamstatsfox.model.response.MatchesDetailsResponse;
import com.acadev.teamstatsfox.service.CardsService;
import com.acadev.teamstatsfox.service.GoalsService;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.service.PresentsService;
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

	public Matches create(Matches request) {
		Matches matches = Matches.builder().id(getNextId()).datetime(request.getDatetime())
				.competition(request.getCompetition()).description(request.getDescription())
				.opponent(request.getOpponent()).ourGoals(request.getOurGoals()).rivalGoals(request.getRivalGoals()).build();

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

	public Matches create(MatchDetailsRequest matchDetails) {
		return null;
	}

}
