package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Cards;
import com.acadev.teamstatsfox.utils.enums.ECardType;

public interface CardsService {

	String echo();
	
	List<Cards> getCards();

	List<Cards> getCardsByMatchId(Long id);

	Cards create(Cards cardsEntity);

	void deleteByMatchId(Long id);

	List<Cards> getCardsPlayerId(Long id);

	List<Cards> getCardsByPlayerIdAndTypeAndByMatchesIds(Long playerId, ECardType type, List<Long> matchesIds);

	List<Cards> getCardsByPlayerIdAndType(Long id, ECardType type);

}
