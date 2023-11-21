package com.acadev.teamstatsfox.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.response.StatsFromCvsResponse;
import com.acadev.teamstatsfox.service.PublicService;
import com.acadev.teamstatsfox.utils.FunctionUtils;

@Service
public class PublicServiceImpl implements PublicService {

	@Autowired
	private ReadCvsServiceImpl readCvsService;

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
		response.setPlayers(readCvsService.getStatsPlayers());

		return ResponseHandler.generateResponse(response, HttpStatus.OK);
	}

}
