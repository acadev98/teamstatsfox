package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Cards;

public interface CardsService {

	String echo();
	
	List<Cards> getCards();

	List<Cards> getCardsByMatchId(Long id);

}
