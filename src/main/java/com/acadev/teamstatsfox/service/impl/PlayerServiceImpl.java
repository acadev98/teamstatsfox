package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
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

	public Player create(Player player) {
		return repository.save(player);
	}

	public Player update(Long id, Player player) {
		Optional<Player> playerToUpdate = repository.findById(id);

		if (playerToUpdate.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		Player playerUpdated = playerToUpdate.get();
			playerUpdated.setDni(player.getDni());
			playerUpdated.setLastname(player.getLastname());
			playerUpdated.setName(player.getName());
			playerUpdated.setPosition(player.getPosition());
			playerUpdated.setSecondPosition(player.getSecondPosition());
			playerUpdated.setBirthday(player.getBirthday());
			playerUpdated.setPlayingSince(player.getPlayingSince());

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
