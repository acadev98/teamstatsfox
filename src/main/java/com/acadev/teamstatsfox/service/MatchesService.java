package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.model.response.MatchesDetailsResponse;

public interface MatchesService {

	String echo();

	List<Matches> getMatches();

	Matches create(Matches matches);

	Matches getMatch(Long id);

	MatchesDetailsResponse getMatchDetails(Long id);

}
