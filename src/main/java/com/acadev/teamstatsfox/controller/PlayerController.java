package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return service.echo();
	}

	@GetMapping("/all")
	public ResponseEntity<Object> get() {
		return service.get();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPlayersById(@PathVariable("id") Long id) {
		return service.get(id);
	}

}
