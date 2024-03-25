package com.acadev.teamstatsfox.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.entity.OpponentsTournment;
import com.acadev.teamstatsfox.database.entity.Tournments;
import com.acadev.teamstatsfox.database.repository.OpponentsRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.response.OpponentsDetailsResponse;
import com.acadev.teamstatsfox.model.response.PrevAndNextOpponentsResponse;
import com.acadev.teamstatsfox.service.MatchesService;
import com.acadev.teamstatsfox.service.OpponentsService;
import com.acadev.teamstatsfox.service.OpponentsTournmentsService;
import com.acadev.teamstatsfox.service.TournmentsService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class OpponentsServiceImpl implements OpponentsService {

	@Autowired
	private OpponentsRepository repository;

	@Autowired
	private MatchesService matchesService;

	@Autowired
	private TournmentsService tournmentsService;

	@Autowired
	private OpponentsTournmentsService opponentTournmentsService;

	public Long getNextId() {
		Optional<Opponents> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId() + 1);
		return 1L;
	}

	public String echo() {
		return "opponents echo message";
	}

	public List<Opponents> getOpponents() {

		List<Opponents> opponents = repository.findAll();
		if (opponents.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		List<Opponents> opponentsListOrdered = opponents.stream().sorted(Comparator.comparing(Opponents::getName))
				.collect(Collectors.toList());

		return opponentsListOrdered;
	}

	public List<OpponentsDetailsResponse> getOpponentsDetails() {

		List<Opponents> opponents = getOpponents();
		List<OpponentsDetailsResponse> opponentsDetails = new ArrayList<>();

		for (Opponents op : opponents) {
			List<Matches> matches = matchesService.getMatchesByOpponentId(op.getId());
			OpponentsDetailsResponse opponentsDetailsResponse = OpponentsDetailsResponse.builder().matches(matches)
					.opponent(op).build();

			opponentsDetails.add(opponentsDetailsResponse);
		}

		return opponentsDetails;
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

	public OpponentsDetailsResponse getOpponentDetailsById(Long id) {

		Opponents opponent = getOpponentById(id);

		List<Matches> matches = matchesService.getMatchesByOpponentId(opponent.getId());

		List<Tournments> tournments = new ArrayList<>();
		List<OpponentsTournment> opponentsTournments = opponentTournmentsService
				.getTournmentsByOpponentId(opponent.getId());
		for (OpponentsTournment ot : opponentsTournments) {
			tournments.add(tournmentsService.getTournmentById(ot.getTournmentId()));
		}

		OpponentsDetailsResponse opponentsDetailsResponse = OpponentsDetailsResponse.builder().matches(matches)
				.opponent(opponent).tournments(tournments).build();

		return opponentsDetailsResponse;
	}

	public Opponents delete(Long id) {

		Optional<Opponents> opponent = repository.findById(id);
		if (opponent.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);
		repository.delete(opponent.get());

		return opponent.get();
	}

	public PrevAndNextOpponentsResponse getOpponentsPrevAndNext(Long id) {

		List<Opponents> opponentsList = getOpponents();

		List<Opponents> opponentsListOrdered = opponentsList.stream().sorted(Comparator.comparing(Opponents::getName))
				.collect(Collectors.toList());

		Opponents opponent = getOpponentById(id);

		Integer index = opponentsListOrdered.indexOf(opponent);
		Integer lastIndex = opponentsListOrdered.size() - 1;

		Integer indexPrev = (index == 0 ? lastIndex : index - 1);
		Integer indexNext = (index == lastIndex ? 0 : index + 1);

		Opponents prev = opponentsListOrdered.get(indexPrev);
		Opponents next = opponentsListOrdered.get(indexNext);

		return PrevAndNextOpponentsResponse.builder().prev(prev).next(next).build();

	}

	public Opponents update(Long id, Opponents opponent) {

		Opponents opponentEntity = getOpponentById(id);

		opponentEntity.setName(opponent.getName().toUpperCase());

		return repository.save(opponentEntity);
	}

}
