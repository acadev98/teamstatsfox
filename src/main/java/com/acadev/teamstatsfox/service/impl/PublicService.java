package com.acadev.teamstatsfox.service.impl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.response.StatsFromCvsResponse;
import com.acadev.teamstatsfox.utils.FunctionUtils;

@Service
public class PublicService {

	@Autowired
	private ReadCvsService readCvsService;

	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse("echo message", HttpStatus.OK);
	}

	public ResponseEntity<Object> statsFromStaticData() {
		StatsFromCvsResponse response = new StatsFromCvsResponse();
		response.setDateImport(new Date());
		response.setPlayers(FunctionUtils.generateArrayListOfPlayerStatsFromCvs());

		return ResponseHandler.generateResponse(response, HttpStatus.OK);
	}

	public ResponseEntity<Object> statsFromCvs() throws IOException {

		StatsFromCvsResponse response = new StatsFromCvsResponse();
		response.setDateImport(new Date());
		response.setPlayers(readCvsService.getStatsPlayers());

		return ResponseHandler.generateResponse(response, HttpStatus.OK);
	}

}
