package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Tournments;
import com.acadev.teamstatsfox.model.response.PlayerStatisticsResponse;
import com.acadev.teamstatsfox.model.response.TournmentsDetailsResponse;

public interface TournmentsService {

	String echo();
	
	List<Tournments> getTournments();
	
	Tournments create(Tournments tournment);
	
	Tournments getTournmentById(Long id);

	TournmentsDetailsResponse getPlayersByTournmentId(Long id);

	List<TournmentsDetailsResponse> getTournmentsAndOpponents();

	List<PlayerStatisticsResponse> getStatisticsPlayersByTournmentId(Long id);

}
