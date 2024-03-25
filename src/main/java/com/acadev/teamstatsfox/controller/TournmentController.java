package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.request.TournmentDetailsRequest;
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
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_TOURNMENTS, HttpStatus.OK,
				service.getTournments());
	}

	@GetMapping("/details")
	public ResponseEntity<Object> getTournmentsDetails() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_TOURNMENTS, HttpStatus.OK,
				service.getTournmentsDetails());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENTS_FOUND, HttpStatus.OK,
				service.getTournmentById(id));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteMatch(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENT_DELETED, HttpStatus.OK, service.delete(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> create(@RequestBody TournmentDetailsRequest tournmentDetails) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENT_CREATED, HttpStatus.CREATED,
				service.create(tournmentDetails));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> update(@PathVariable("id") Long id,
			@RequestBody TournmentDetailsRequest tournmentDetails) {
		return ResponseHandler.generateResponse(MessagesUtils.TOURNMENT_UPDATED, HttpStatus.OK,
				service.update(id, tournmentDetails));
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<Object> getTournmentsDetailsById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_TOURNMENTS_DETAILS, HttpStatus.OK,
				service.getTournmentsDetailsById(id));
	}

	@GetMapping("/{id}/statistics")
	public ResponseEntity<Object> getStatisticsByTournmentId(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.STATISTICS_OF_TORUNMENT_PLAYERS, HttpStatus.OK,
				service.getStatisticsPlayersByTournmentId(id));
	}

}
