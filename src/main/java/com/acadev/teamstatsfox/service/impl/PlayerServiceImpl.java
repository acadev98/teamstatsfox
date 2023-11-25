package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.PlayerService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository repository;

	public ResponseEntity<Object> echo() {
		return ResponseHandler.generateResponse(null, HttpStatus.OK, "player echo message");
	}

	public ResponseEntity<Object> getPlayers() {

		List<Player> players = (List<Player>) repository.findAll();

		if (players.isEmpty())
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, MessagesUtils.RESULT_NOT_FOUND);

		return ResponseHandler.generateResponse(MessagesUtils.LIST_OF_PLAYERS, HttpStatus.OK, players);

	}

	public ResponseEntity<Object> getPlayer(Long id) {

		Optional<Player> player = repository.findById(id);

		if (player.isEmpty())
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, MessagesUtils.RESULT_NOT_FOUND);

		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_FOUND, HttpStatus.OK, player.get());

	}

	public ResponseEntity<Object> create(Player player) {

		Player playerSaved = repository.save(player);
		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_CREATED, HttpStatus.CREATED, playerSaved);
	}

	public ResponseEntity<Object> update(Long id, Player player) {
		Optional<Player> playerToUpdate = repository.findById(id);

		if (playerToUpdate.isEmpty())
			return ResponseHandler.generateResponse(MessagesUtils.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);

		Player playerUpdated = playerToUpdate.get();
		playerUpdated.setDni(player.getDni());
		playerUpdated.setLastname(player.getLastname());
		playerUpdated.setName(player.getName());
		playerUpdated.setPosition(player.getPosition());
		playerUpdated.setSecondPosition(player.getSecondPosition());
		playerUpdated.setBirthday(player.getBirthday());
		playerUpdated.setPlayingSince(player.getPlayingSince());

		repository.save(playerUpdated);

		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_UPDATED, HttpStatus.ACCEPTED, playerUpdated);
	}

	public ResponseEntity<Object> delete(Long id) {
		Optional<Player> player = repository.findById(id);

		if (player.isEmpty())
			return ResponseHandler.generateResponse(MessagesUtils.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);

		repository.delete(player.get());

		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_DELETED, HttpStatus.OK);
	}

}
