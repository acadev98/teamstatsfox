package com.acadev.teamstatsfox.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.response.PlayerStatsResponse;
import com.acadev.teamstatsfox.model.response.StatsFromCvsResponse;
import com.acadev.teamstatsfox.service.PublicService;
import com.acadev.teamstatsfox.utils.FunctionsUtils;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@Service
public class PublicServiceImpl implements PublicService {

	@Autowired
	private PlayerRepository repository;

	@Autowired
	private ReadCvsServiceImpl service;

	@Autowired
	private MapperService mapperService;

	public ResponseEntity<Object> echo() {
		return ResponseHandler.generateResponse("echo message", HttpStatus.OK);
	}

	public ResponseEntity<Object> getDataStatic() {
		StatsFromCvsResponse response = new StatsFromCvsResponse();
		response.setDateImport(new Date());
		response.setPlayers(FunctionsUtils.generateArrayListOfPlayerStatsFromCvs());

		return ResponseHandler.generateResponse("", HttpStatus.OK, response);
	}

	public ResponseEntity<Object> getDataCvs() {

		StatsFromCvsResponse response = new StatsFromCvsResponse();
		response.setDateImport(new Date());
		response.setPlayers(service.getStatsPlayers());

		return ResponseHandler.generateResponse("", HttpStatus.OK, response);
	}

	public ResponseEntity<Object> getPlayers() {
		List<Player> players = (List<Player>) repository.findAll();

		List<PlayerStatsResponse> playersDTOResponseList = players.stream().map(mapperService::convertToDto)
				.collect(Collectors.toList());

		if (playersDTOResponseList.isEmpty())
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, MessagesUtils.RESULT_NOT_FOUND);

		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_PLAYERS, HttpStatus.OK, playersDTOResponseList);
	}

}
