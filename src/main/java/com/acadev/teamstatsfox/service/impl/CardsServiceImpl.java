package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Cards;
import com.acadev.teamstatsfox.database.repository.CardsRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.service.CardsService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class CardsServiceImpl implements CardsService {

	@Autowired
	private CardsRepository repository;
	
	public Long getNextId() {
		Optional<Cards> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
				return (entityMaxId.get().getId()+1);
        return 1L;
    }

	public String echo() {
		return "cards echo message";
	}

	public List<Cards> getCards() {

		List<Cards> cards = repository.findAll();
		if (cards.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return cards;
	}

	public List<Cards> getCardsByMatchId(Long id) {
		return repository.findAllByMatchId(id);
	}

	public Cards create(Cards request) {
		request.setId(getNextId());
		return repository.save(request);
	}
	
	public void deleteByMatchId(Long matchId) {
		List<Cards> cardsByMatchId = repository.findAllByMatchId(matchId);		
		if (cardsByMatchId.size()>0) {
			repository.deleteAll(cardsByMatchId);
		}
	}
	
}
