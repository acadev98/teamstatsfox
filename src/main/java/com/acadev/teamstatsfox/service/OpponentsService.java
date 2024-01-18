package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Opponents;

public interface OpponentsService {

	String echo();
	
	List<Opponents> getOpponents();
	
	Opponents create(Opponents tournment);
	
	Opponents getOpponentById(Long id);

}
