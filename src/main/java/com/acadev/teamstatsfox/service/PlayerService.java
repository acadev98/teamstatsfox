package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.model.request.PlayerRequest;

public interface PlayerService {

	Players create(PlayerRequest player);

	Players delete(Long id);

	String echo();

	Players getPlayer(Long id);

	List<Players> getPlayers();

	List<Players> getPlayersActives();

	Players update(Long id, PlayerRequest player);

	List<Integer> findNumbers();

}
