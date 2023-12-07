package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.database.repository.MatchesRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class MatchesServiceImpl implements MatchesService {

	@Autowired
	private MatchesRepository repository;
	
	public Long getNextId() {
		Optional<Matches> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
				return (entityMaxId.get().getId()+1);
        return 1L;
    }

	public String echo() {
		return "matches echo message";
	}

	public List<Matches> getMatches() {

		List<Matches> matches = (List<Matches>) repository.findAll();
		if (matches.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return matches;
	}	

	public Matches create(Matches request) {
		Matches matches = Matches.builder().id(getNextId()).datetime(request.getDatetime())
				.competition(request.getCompetition()).description(request.getDescription())
				.opponent(request.getOpponent()).build();

		return repository.save(matches);
	}

}
