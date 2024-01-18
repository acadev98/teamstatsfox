package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.OpponentsTournment;
import com.acadev.teamstatsfox.database.repository.OpponentsTournmentRepository;
import com.acadev.teamstatsfox.service.OpponentsTournmentService;

@Service
public class OpponentsTournmentServiceImpl implements OpponentsTournmentService {

	@Autowired
	private OpponentsTournmentRepository repository;
	
	public Long getNextId() {
		Optional<OpponentsTournment> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
				return (entityMaxId.get().getId()+1);
        return 1L;
    }

	public String echo() {
		return "opponents tournment echo message";
	}

	public List<OpponentsTournment> getOpponentsByTournmentId(Long id) {
		return repository.findAllByTournmentId(id);
	}

	public OpponentsTournment create(OpponentsTournment opponentTournment) {
		opponentTournment.setId(getNextId());

		return repository.save(opponentTournment);
	}
	
	public void deleteByTournmentId(Long tournmentId) {
		List<OpponentsTournment> opponentsByTournmentId = repository.findAllByTournmentId(tournmentId);		
		if (opponentsByTournmentId.size()>0) {
			repository.deleteAll(opponentsByTournmentId);
		}
	}
}
