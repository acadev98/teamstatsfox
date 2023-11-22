package com.acadev.teamstatsfox.service;

import org.springframework.http.ResponseEntity;

import com.acadev.teamstatsfox.database.entity.Player;

public interface PlayerService {

	ResponseEntity<Object> echo();

	ResponseEntity<Object> getPlayers();

	ResponseEntity<Object> getPlayer(Long id);

	ResponseEntity<Object> create(Player player);

	ResponseEntity<Object> update(Long id, Player player);

	ResponseEntity<Object> delete(Long id);

}
