package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Tournment;
import com.acadev.teamstatsfox.model.response.TournmentsDetailsResponse;

public interface TournmentService {

	String echo();
	
	List<Tournment> getTourments();
	
	Tournment create(Tournment tournment);
	
	Tournment getTourment(Long id);

	TournmentsDetailsResponse getPlayersByTourmentId(Long id);

}
