package com.acadev.teamstatsfox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.repository.PlayerRepositoy;
import com.acadev.teamstatsfox.handler.ResponseHandler;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepositoy repository;
	
	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse("player echo message", HttpStatus.OK);
	}

	public ResponseEntity<Object> findAll() {
		return ResponseHandler.generateResponse(repository.findAll(), HttpStatus.OK);
	}
	
}
