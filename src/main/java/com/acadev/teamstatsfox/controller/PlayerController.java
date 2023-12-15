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
import com.acadev.teamstatsfox.model.request.PlayerRequest;
import com.acadev.teamstatsfox.service.PlayerService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	private PlayerService service;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> createPlayer(@Valid @RequestBody PlayerRequest player) {
		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_CREATED, HttpStatus.CREATED,
				service.create(player));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deletePlayer(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_DELETED, HttpStatus.OK, service.delete(id));
	}

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return ResponseHandler.generateResponse(service.echo(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> getPlayers() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_PLAYERS, HttpStatus.OK, service.getPlayers());
	}

	@GetMapping("/actives")
	public ResponseEntity<Object> getPlayersActive() {
		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_PLAYERS_ACTIVES, HttpStatus.OK, service.getPlayersActives());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPlayersById(@PathVariable("id") Long id) {
		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_FOUND, HttpStatus.OK, service.getPlayer(id));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updatePlayer(@PathVariable("id") Long id, @RequestBody PlayerRequest player) {
		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_UPDATED, HttpStatus.ACCEPTED,
				service.update(id, player));
	}

}
