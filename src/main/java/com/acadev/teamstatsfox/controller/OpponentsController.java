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

import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.OpponentsService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@RestController
@RequestMapping("/api/opponents")
public class OpponentsController {

	@Autowired
	private OpponentsService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse(service.echo(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> get() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_OPPONENTS, HttpStatus.OK, service.getOpponents());
	}

	@GetMapping("/details")
	public ResponseEntity<Object> getOpponentDetails() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_OPPONENTS, HttpStatus.OK,
				service.getOpponentsDetails());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.OPPONENTS_FOUND, HttpStatus.OK,
				service.getOpponentById(id));
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<Object> getDetailsById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.OPPONENTS_FOUND, HttpStatus.OK,
				service.getOpponentDetailsById(id));
	}

	@GetMapping("/{id}/prevandnext")
	public ResponseEntity<Object> getOpponentsPrevAndNextById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.OPPONENTS_FOUND, HttpStatus.OK,
				service.getOpponentsPrevAndNext(id));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.OPPONENTS_DELETED, HttpStatus.OK, service.delete(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> create(@RequestBody Opponents opponents) {
		return ResponseHandler.generateResponse(MessagesUtils.OPPONENTS_CREATED, HttpStatus.CREATED,
				service.create(opponents));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Opponents opponents) {
		return ResponseHandler.generateResponse(MessagesUtils.OPPONENTS_UPDATED, HttpStatus.OK,
				service.update(id, opponents));
	}

}
