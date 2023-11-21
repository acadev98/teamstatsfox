package com.acadev.teamstatsfox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.repository.PlayerRepositoy;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepositoy repository;

	public ResponseEntity<Object> echo() {
		return ResponseHandler.generateResponse("player echo message", HttpStatus.OK);
	}

	public ResponseEntity<Object> get() {
		return ResponseHandler.generateResponse(repository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Object> get(Long id) {
		return ResponseHandler.generateResponse(repository.findById(id), HttpStatus.OK);
	}

}
