package com.acadev.teamstatsfox.service.impl;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.response.StatsFromCvsResponse;
import com.acadev.teamstatsfox.utils.FunctionUtils;

@Service
public class PublicService {
	
	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse("echo message", HttpStatus.OK);
	}

	public ResponseEntity<Object> statsFromStaticData() {
		StatsFromCvsResponse response = new StatsFromCvsResponse();
			response.setDateImport(new Date());
			response.setPlayers(FunctionUtils.generateArrayListOfPlayerStatsFromCvs());
		
		return ResponseHandler.generateResponse(response, HttpStatus.OK);
	}

	public ResponseEntity<Object> statsFromCvs() {
		StatsFromCvsResponse response = new StatsFromCvsResponse();
			response.setDateImport(new Date());
			response.setPlayers(null);
		
		return ResponseHandler.generateResponse(response, HttpStatus.OK);
	}

}
