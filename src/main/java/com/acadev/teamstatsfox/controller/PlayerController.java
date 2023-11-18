package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.service.impl.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return service.echoTest();
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllPlayers() {
		return service.findAll();
	}
	
}
