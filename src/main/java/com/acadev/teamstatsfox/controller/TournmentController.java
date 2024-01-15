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

import com.acadev.teamstatsfox.database.entity.Tournment;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.TournmentService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@RestController
@RequestMapping("/api/tournments")
public class TournmentController {

	@Autowired
	private TournmentService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse(service.echo(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> get() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_TOURNMENTS, HttpStatus.OK, service.getTourments());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENTS_FOUND, HttpStatus.OK, service.getTourment(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> create(@RequestBody Tournment tournment) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENT_CREATED, HttpStatus.CREATED,
				service.create(tournment));
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<Object> getPlayersByTournmentId(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_PLAYERS_TOURNMENTS, HttpStatus.OK, service.getPlayersByTourmentId(id));
	}

}
