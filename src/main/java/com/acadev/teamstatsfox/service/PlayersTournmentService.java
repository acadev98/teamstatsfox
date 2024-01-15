package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.PlayersTourment;

public interface PlayersTournmentService {

	String echo();

	List<PlayersTourment> getPlayersByTournmentId(Long id);
	
	PlayersTourment create(PlayersTourment request);

	void deleteByTournmentId(Long id);

}
