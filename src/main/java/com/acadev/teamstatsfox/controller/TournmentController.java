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

import com.acadev.teamstatsfox.database.entity.Tournments;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.TournmentsService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@RestController
@RequestMapping("/api/tournments")
public class TournmentController {

	@Autowired
	private TournmentsService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse(service.echo(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> get() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_TOURNMENTS, HttpStatus.OK, service.getTournments());
	}

	@GetMapping("/opponents")
	public ResponseEntity<Object> getTournmentsAndOpponents() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_TOURNMENTS, HttpStatus.OK, service.getTournmentsAndOpponents());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENTS_FOUND, HttpStatus.OK, service.getTournmentById(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> create(@RequestBody Tournments tournment) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENT_CREATED, HttpStatus.CREATED,
				service.create(tournment));
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<Object> getPlayersByTournmentId(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_TOURNMENTS_DETAILS, HttpStatus.OK, service.getPlayersByTournmentId(id));
	}

	@GetMapping("/{id}/statistics")
	public ResponseEntity<Object> getStatisticsByTournmentId(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.STATISTICS_OF_TORUNMENT_PLAYERS, HttpStatus.OK, service.getStatisticsPlayersByTournmentId(id));
	}

}
