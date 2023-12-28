package com.acadev.teamstatsfox.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.acadev.teamstatsfox.model.response.AssistsPlayedResponse;
import com.acadev.teamstatsfox.model.response.GamesPlayedResponse;
import com.acadev.teamstatsfox.model.response.GoalsPlayedResponse;

public interface PublicService {

	ResponseEntity<Object> echo();

	ResponseEntity<Object> getDataCvs();

	ResponseEntity<Object> getDataStatic();

	ResponseEntity<Object> getPlayers();

	List<AssistsPlayedResponse> topAssists();

	List<GamesPlayedResponse> topGames();

	List<GoalsPlayedResponse> topGoals();

	List<Integer> availableNumbers();

}
