package com.acadev.teamstatsfox.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.database.repository.PlayerRepositoy;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepositoy repository;

	public ResponseEntity<Object> echo() {
		return ResponseHandler.generateResponse("player echo message", HttpStatus.OK);
	}

	public ResponseEntity<Object> get() {
		return ResponseHandler.generateResponse(repository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Object> get(Long id) {
		return ResponseHandler.generateResponse(repository.findById(id), HttpStatus.OK);
	}

	public ResponseEntity<Object> create(Player player) {
		return ResponseHandler.generateResponse(repository.save(player), HttpStatus.OK);
	}

	public ResponseEntity<Object> update(Long id, Player player) {
		Optional<Player> playerToUpdate = repository.findById(id);
		
		if (playerToUpdate.isPresent()) {
			Player playerUpdated = playerToUpdate.get();
			playerUpdated.setDni(player.getDni());
			playerUpdated.setLastname(player.getLastname());
			playerUpdated.setName(player.getName());
			playerUpdated.setPosition(player.getPosition());
			playerUpdated.setSecondPosition(player.getSecondPosition());
			
			playerToUpdate = Optional.of(repository.save(playerUpdated));
		}
		
		return ResponseHandler.generateResponse(playerToUpdate, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> delete(Long id) {
		Optional<Player> playerToDelete = repository.findById(id);
		
		if (playerToDelete.isPresent()) {
			repository.delete(playerToDelete.get());
		}
		
		return ResponseHandler.generateResponse(playerToDelete, HttpStatus.ACCEPTED);
	}

}
