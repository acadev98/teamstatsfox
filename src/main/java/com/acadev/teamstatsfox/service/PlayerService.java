package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.model.request.PlayerRequest;

public interface PlayerService {

	String echo();

	List<Player> getPlayers();

	Player getPlayer(Long id);

	Player create(PlayerRequest player);

	Player update(Long id, PlayerRequest player);

	Player delete(Long id);

}
