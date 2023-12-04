package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.request.PlayerRequest;
import com.acadev.teamstatsfox.service.PlayerService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository repository;

	public String echo() {
		return "player echo message";
	}

	public List<Player> getPlayers() {

		List<Player> players = (List<Player>) repository.findAll();
		if (players.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return players;
	}

	public Player getPlayer(Long id) {

		Optional<Player> player = repository.findById(id);
		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return player.get();
	}

	public Player create(PlayerRequest request) {
		Player player = Player.builder()
			.id(request.getId())
			.dni(request.getDni())
			.lastname(request.getLastname().toUpperCase())
			.name(request.getName().toUpperCase())
			.position(request.getPosition().toUpperCase())
			.secondPosition(request.getSecondPosition().toUpperCase())
			.active(request.getActive())
			.birthday(request.getBirthday())
			.playingSince(request.getPlayingSince())
			.build();
		
		return repository.save(player);
	}

	public Player update(Long id, PlayerRequest request) {
		Optional<Player> player = repository.findById(id);

		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		Player playerUpdated = player.get();
			playerUpdated.setDni(request.getDni());
			playerUpdated.setLastname(request.getLastname().toUpperCase());
			playerUpdated.setName(request.getName().toUpperCase());
			playerUpdated.setPosition(request.getPosition().toUpperCase());
			playerUpdated.setSecondPosition(request.getSecondPosition().toUpperCase());
			playerUpdated.setBirthday(request.getBirthday());
			playerUpdated.setPlayingSince(request.getPlayingSince());

		repository.save(playerUpdated);

		return repository.save(playerUpdated);
	}

	public Player delete(Long id) {
		Optional<Player> player = repository.findById(id);

		if (player.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		repository.delete(player.get());
		return player.get();
	}

}
