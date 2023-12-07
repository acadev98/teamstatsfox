package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/matches")
public class MatchesController {

	@Autowired
	private MatchesService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse(service.echo(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> get() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_MATCHES, HttpStatus.OK, service.getMatches());
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> createMatches(@Valid @RequestBody Matches matches) {
		return ResponseHandler.generateResponse(MessagesUtils.MATCHES_CREATED, HttpStatus.CREATED,
				service.create(matches));
	}

}
