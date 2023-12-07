package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Matches;

public interface MatchesService {

	String echo();

	List<Matches> getMatches();

	Matches create(Matches matches);

}
