package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return service.echo();
	}

	@GetMapping
	public ResponseEntity<Object> get() {
		return service.getPlayers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPlayersById(@PathVariable("id") Long id) {
		return service.getPlayer(id);
	}

	@PostMapping
	public ResponseEntity<Object> createPlayer(@RequestBody Player player) {
		return service.create(player);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updatePlayer(@PathVariable("id") Long id, @RequestBody Player player) {
		return service.update(id, player);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePlayer(@PathVariable("id") Long id) {
		return service.delete(id);
	}

}
