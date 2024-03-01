package com.acadev.teamstatsfox.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.response.AssistsPlayedResponse;
import com.acadev.teamstatsfox.model.response.GamesPlayedResponse;
import com.acadev.teamstatsfox.model.response.GoalsPlayedResponse;
import com.acadev.teamstatsfox.model.response.PlayerStatsFromCvs;
import com.acadev.teamstatsfox.model.response.PlayerStatsResponse;
import com.acadev.teamstatsfox.model.response.StatsFromCvsResponse;
import com.acadev.teamstatsfox.service.PlayerService;
import com.acadev.teamstatsfox.service.PublicService;
import com.acadev.teamstatsfox.service.ReadCvsService;
import com.acadev.teamstatsfox.utils.FunctionsUtils;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@Service
public class PublicServiceImpl implements PublicService {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private MapperService mapperService;

	public ResponseEntity<Object> echo() {
		return ResponseHandler.generateResponse("echo message", HttpStatus.OK);
	}

	public ResponseEntity<Object> getPlayers() {
		List<Players> players = playerService.getPlayers();

		List<PlayerStatsResponse> playersDTOResponseList = players.stream().map(mapperService::convertToDto)
				.collect(Collectors.toList());

		if (playersDTOResponseList.isEmpty())
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, MessagesUtils.RESULT_NOT_FOUND);

		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_PLAYERS, HttpStatus.OK, playersDTOResponseList);
	}

//	public List<AssistsPlayedResponse> topAssists() {
//
//		ArrayList<PlayerStatsFromCvs> playersStats = service.getStatsPlayers();
//
//		Comparator<PlayerStatsFromCvs> compareByAssistAndMatchesPlayed = Comparator
//				.comparing(PlayerStatsFromCvs::getAssists).reversed();
//
//		ArrayList<PlayerStatsFromCvs> sortedList = playersStats.stream().sorted(compareByAssistAndMatchesPlayed)
//				.collect(Collectors.toCollection(ArrayList::new));
//
//		List<AssistsPlayedResponse> responseList = sortedList.stream().limit(10).map(mapperService::convertToDtoAssists)
//				.collect(Collectors.toList());
//
//		return responseList;
//	}
//
//	public List<GamesPlayedResponse> topGames() {
//
//		ArrayList<PlayerStatsFromCvs> playersStats = service.getStatsPlayers();
//
//		Comparator<PlayerStatsFromCvs> compareByMatchsPlayed = Comparator.comparing(PlayerStatsFromCvs::getMatches)
//				.reversed();
//
//		ArrayList<PlayerStatsFromCvs> sortedList = playersStats.stream().sorted(compareByMatchsPlayed)
//				.collect(Collectors.toCollection(ArrayList::new));
//
//		List<GamesPlayedResponse> responseList = sortedList.stream().limit(10).map(mapperService::convertToDtoGames)
//				.collect(Collectors.toList());
//
//		return responseList;
//	}
//
//	public List<GoalsPlayedResponse> topGoals() {
//
//		ArrayList<PlayerStatsFromCvs> playersStats = service.getStatsPlayers();
//
//		Comparator<PlayerStatsFromCvs> compareByMatchsPlayed = Comparator.comparing(PlayerStatsFromCvs::getGoals)
//				.reversed();
//
//		ArrayList<PlayerStatsFromCvs> sortedList = playersStats.stream().sorted(compareByMatchsPlayed)
//				.collect(Collectors.toCollection(ArrayList::new));
//
//		List<GoalsPlayedResponse> responseList = sortedList.stream().limit(10).map(mapperService::convertToDtoGoals)
//				.collect(Collectors.toList());
//
//		return responseList;
//	}

	public List<Integer> availableNumbers() {
		
		List<Integer> numbersPlayers = playerService.findNumbers();
		List<Integer> availableNumbers = new ArrayList<>();
		
		for (int i = 1; i < 100; i++) {
			if(!numbersPlayers.contains(i)) {
				availableNumbers.add(i);				
			}
		}
		
		return availableNumbers;
	}

}
