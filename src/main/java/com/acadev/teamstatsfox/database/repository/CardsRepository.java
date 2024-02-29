package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Cards;
import com.acadev.teamstatsfox.utils.enums.ECardType;

public interface CardsRepository extends PagingAndSortingRepository<Cards, Long>, JpaRepository<Cards, Long> {

	Optional<Cards> findTopByOrderByIdDesc();

	List<Cards> findAllByMatchId(Long id);

	List<Cards> findAllByPlayerId(Long id);

	List<Cards> findAllByPlayerIdAndTypeAndMatchIdIn(Long playerId, ECardType type, List<Long> matchesIds);

	List<Cards> findAllByPlayerIdAndType(Long playerId, ECardType type);
}