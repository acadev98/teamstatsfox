package com.acadev.teamstatsfox.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.acadev.teamstatsfox.model.response.AssistsPlayedResponse;
import com.acadev.teamstatsfox.model.response.GamesPlayedResponse;
import com.acadev.teamstatsfox.model.response.GoalsPlayedResponse;

public interface PublicService {

	ResponseEntity<Object> echo();

	ResponseEntity<Object> getDataStatic();

	ResponseEntity<Object> getDataCvs();

	ResponseEntity<Object> getPlayers();

	List<GamesPlayedResponse> topGames();

	List<GoalsPlayedResponse> topGoals();

	List<AssistsPlayedResponse> topAssists();

}
