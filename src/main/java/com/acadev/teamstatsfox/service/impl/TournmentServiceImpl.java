package com.acadev.teamstatsfox.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.PlayersTourment;
import com.acadev.teamstatsfox.database.entity.Tournment;
import com.acadev.teamstatsfox.database.repository.PlayerRepository;
import com.acadev.teamstatsfox.database.repository.TournmentRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.response.TournmentsDetailsResponse;
import com.acadev.teamstatsfox.service.PlayersTournmentService;
import com.acadev.teamstatsfox.service.TournmentService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class TournmentServiceImpl implements TournmentService {

	@Autowired
	private TournmentRepository repository;

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PlayersTournmentService playersTournmentService;

	@Autowired
	private MapperService mapperService;

	public Long getNextId() {
		Optional<Tournment> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId()+1);
		return 1L;
	}

	public String echo() {
		return "tournment echo message";
	}

	public List<Tournment> getTourments() {

		List<Tournment> tourments = repository.findAll();
		if (tourments.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);
		
		List<Tournment> tourmentsListOrdered = tourments.stream()
				  .sorted(Comparator.comparing(Tournment::getStartDate))
				  .collect(Collectors.toList());

		return tourmentsListOrdered;
	}

	public Tournment create(Tournment tournment) {
		tournment.setId(getNextId());
		return repository.save(tournment);
	}

	public Tournment getTourment(Long id) {

		Optional<Tournment> tourment = repository.findById(id);
		if (tourment.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return tourment.get();
	}

	public TournmentsDetailsResponse getPlayersByTourmentId(Long id) {
		
		Tournment tournment = getTourment(id);
		List<PlayersTourment> playersTournments = playersTournmentService.getPlayersByTournmentId(id);
		
		List<Players> players = new ArrayList<>();
		if (!playersTournments.isEmpty()) {
			List<Long> playersIds= playersTournments.stream().map(mapperService::getPlayerIds).collect(Collectors.toList());

			for (Long playerId : playersIds) {
				players.add(playerRepository.findById(playerId).get());
			}
		}
		
		TournmentsDetailsResponse response = TournmentsDetailsResponse.builder()
				.tournment(tournment)
				.players(players)
				.build();

		return response;
	}

}
