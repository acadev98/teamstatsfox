package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.OpponentsTournment;

public interface OpponentsTournmentService {

	String echo();

	List<OpponentsTournment> getOpponentsByTournmentId(Long id);
	
	OpponentsTournment create(OpponentsTournment request);

	void deleteByTournmentId(Long id);

}
