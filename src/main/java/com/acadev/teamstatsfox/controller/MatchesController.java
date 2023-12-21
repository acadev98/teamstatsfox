package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.request.MatchDetailsRequest;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

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

	@GetMapping("/{id}")
	public ResponseEntity<Object> getMatchesById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.MATCHES_FOUND, HttpStatus.OK, service.getMatch(id));
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<Object> getMatchesDetailsById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.MATCHES_FOUND, HttpStatus.OK, service.getMatchDetails(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> createMatches(@RequestBody MatchDetailsRequest matchDetails) {
		return ResponseHandler.generateResponse(MessagesUtils.MATCHES_CREATED, HttpStatus.CREATED,
				service.create(matchDetails));
	}

}
