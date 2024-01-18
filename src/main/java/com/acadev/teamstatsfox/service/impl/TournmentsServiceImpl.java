package com.acadev.teamstatsfox.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.entity.OpponentsTournment;
import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.PlayersTournment;
import com.acadev.teamstatsfox.database.entity.Tournments;
import com.acadev.teamstatsfox.database.repository.OpponentsRepository;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.database.repository.TournmentRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.response.TournmentsDetailsResponse;
import com.acadev.teamstatsfox.service.OpponentsTournmentService;
import com.acadev.teamstatsfox.service.PlayersTournmentService;
import com.acadev.teamstatsfox.service.TournmentsService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class TournmentsServiceImpl implements TournmentsService {

	@Autowired
	private TournmentRepository repository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private OpponentsRepository opponentRepository;
	
	@Autowired
	private PlayersTournmentService playersTournmentService;
	
	@Autowired
	private OpponentsTournmentService opponentsTournmentService;

	@Autowired
	private MapperService mapperService;

	public Long getNextId() {
		Optional<Tournments> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId()+1);
		return 1L;
	}

	public String echo() {
		return "tournment echo message";
	}

	public List<Tournments> getTournments() {

		List<Tournments> tournments = repository.findAll();
		if (tournments.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);
		
		List<Tournments> tournmentsListOrdered = tournments.stream()
				  .sorted(Comparator.comparing(Tournments::getStartDate))
				  .collect(Collectors.toList());

		return tournmentsListOrdered;
	}

	public Tournments create(Tournments tournment) {
		tournment.setId(getNextId());
		return repository.save(tournment);
	}

	public Tournments getTournmentById(Long id) {

		Optional<Tournments> tournment = repository.findById(id);
		if (tournment.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return tournment.get();
	}

	public TournmentsDetailsResponse getPlayersByTournmentId(Long id) {
		
		Tournments tournment = getTournmentById(id);
		List<PlayersTournment> playersTournments = playersTournmentService.getPlayersByTournmentId(id);
		List<OpponentsTournment> opponentsTournments = opponentsTournmentService.getOpponentsByTournmentId(id);
		
		List<Players> players = new ArrayList<>();
		if (!playersTournments.isEmpty()) {
			List<Long> playersIds= playersTournments.stream().map(mapperService::getPlayerIds).collect(Collectors.toList());

			for (Long playerId : playersIds) {
				players.add(playerRepository.findById(playerId).get());
			}
		}
		
		List<Opponents> opponnets = new ArrayList<>();
		if (!opponentsTournments.isEmpty()) {
			List<Long> opponentsIds= opponentsTournments.stream().map(mapperService::getOpponentsIds).collect(Collectors.toList());

			for (Long opponentId : opponentsIds) {
				opponnets.add(opponentRepository.findById(opponentId).get());
			}
		}
		
		TournmentsDetailsResponse response = TournmentsDetailsResponse.builder()
				.tournment(tournment)
				.players(players)
				.opponents(opponnets)
				.build();

		return response;
	}

}
