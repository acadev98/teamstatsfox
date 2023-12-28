package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.model.request.MatchDetailsRequest;
import com.acadev.teamstatsfox.model.response.MatchesDetailsResponse;

public interface MatchesService {

	String echo();

	List<Matches> getMatches();

	MatchesDetailsResponse create(MatchDetailsRequest matchDetails);

	Matches getMatch(Long id);

	MatchesDetailsResponse getMatchDetails(Long id);

}
