package com.acadev.teamstatsfox.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.repository.OpponentsRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.service.OpponentsService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class OpponentsServiceImpl implements OpponentsService {

	@Autowired
	private OpponentsRepository repository;

	public Long getNextId() {
		Optional<Opponents> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId()+1);
		return 1L;
	}

	public String echo() {
		return "opponents echo message";
	}

	public List<Opponents> getOpponents() {

		List<Opponents> opponents = repository.findAll();
		if (opponents.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);
		
		List<Opponents> opponentsListOrdered = opponents.stream()
				  .sorted(Comparator.comparing(Opponents::getName))
				  .collect(Collectors.toList());

		return opponentsListOrdered;
	}

	public Opponents create(Opponents opponent) {
		
		Optional<Opponents> opponentEntity = repository.findByName(opponent.getName().toUpperCase());
		if (opponentEntity.isPresent())
			throw new ApiException(ApiMessage.OPPONENT_ALREADY_EXISTS);
		
		opponent.setId(getNextId());
		opponent.setName(opponent.getName().toUpperCase());
		return repository.save(opponent);
	}

	public Opponents getOpponentById(Long id) {

		Optional<Opponents> opponent = repository.findById(id);
		if (opponent.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return opponent.get();
	}

}
