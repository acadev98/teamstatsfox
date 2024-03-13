package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Tournments;
import com.acadev.teamstatsfox.model.request.TournmentDetailsRequest;
import com.acadev.teamstatsfox.model.response.PlayerStatisticsResponse;
import com.acadev.teamstatsfox.model.response.TournmentsDetailsResponse;

public interface TournmentsService {

	String echo();
	
	List<Tournments> getTournments();
	
	Tournments getTournmentById(Long id);

	TournmentsDetailsResponse getTournmentsDetailsById(Long id);

	List<TournmentsDetailsResponse> getTournmentsDetails();

	List<PlayerStatisticsResponse> getStatisticsPlayersByTournmentId(Long id);

	TournmentsDetailsResponse create(TournmentDetailsRequest tournmentDetails);

	Tournments delete(Long id);

}
