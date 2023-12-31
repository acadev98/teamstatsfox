package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Presents;

public interface PresentsService {

	String echo();

	List<Presents> getPresentsByMatchId(Long id);
	
	Presents create(Presents request);

}
