package com.acadev.teamstatsfox.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.database.repository.PlayerRepositoy;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.response.PlayerDTOResponse;
import com.acadev.teamstatsfox.model.response.StatsFromCvsResponse;
import com.acadev.teamstatsfox.service.PublicService;
import com.acadev.teamstatsfox.utils.FunctionUtils;
import com.acadev.teamstatsfox.utils.Messages;

@Service
public class PublicServiceImpl implements PublicService {

	@Autowired
	private PlayerRepositoy repository;

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
		response.setPlayers(FunctionUtils.generateArrayListOfPlayerStatsFromCvs());

		return ResponseHandler.generateResponse(response, HttpStatus.OK);
	}

	public ResponseEntity<Object> getDataCvs() {

		StatsFromCvsResponse response = new StatsFromCvsResponse();
		response.setDateImport(new Date());
		response.setPlayers(service.getStatsPlayers());

		return ResponseHandler.generateResponse(response, HttpStatus.OK);
	}

	public ResponseEntity<Object> getPlayers() {
		List<Player> players = (List<Player>) repository.findAll();

		List<PlayerDTOResponse> playersDTOResponseList = players.stream().map(mapperService::convertToDto)
				.collect(Collectors.toList());

		if (playersDTOResponseList.isEmpty())
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, Messages.RESULT_NOT_FOUND);

		return ResponseHandler.generateResponse(playersDTOResponseList, Messages.LIST_OF_PLAYERS);
	}

}
