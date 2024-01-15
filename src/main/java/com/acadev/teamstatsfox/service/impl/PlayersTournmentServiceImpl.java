package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.PlayersTourment;
import com.acadev.teamstatsfox.database.repository.PlayersTournmentRepository;
import com.acadev.teamstatsfox.service.PlayersTournmentService;

@Service
public class PlayersTournmentServiceImpl implements PlayersTournmentService {

	@Autowired
	private PlayersTournmentRepository repository;
	
	public Long getNextId() {
		Optional<PlayersTourment> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
				return (entityMaxId.get().getId()+1);
        return 1L;
    }

	public String echo() {
		return "players tournment echo message";
	}

	public List<PlayersTourment> getPlayersByTournmentId(Long id) {
		return repository.findAllByTournmentId(id);
	}

	public PlayersTourment create(PlayersTourment playerTournment) {
		playerTournment.setId(getNextId());

		return repository.save(playerTournment);
	}
	
	public void deleteByTournmentId(Long tournmentId) {
		List<PlayersTourment> playersByTournmentId = repository.findAllByTournmentId(tournmentId);		
		if (playersByTournmentId.size()>0) {
			repository.deleteAll(playersByTournmentId);
		}
	}
}
