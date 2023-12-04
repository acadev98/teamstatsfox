package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Player;

public interface PlayerService {

	String echo();

	List<Player> getPlayers();

	Player getPlayer(Long id);

	Player create(Player player);

	Player update(Long id, Player player);

	Player delete(Long id);

}
