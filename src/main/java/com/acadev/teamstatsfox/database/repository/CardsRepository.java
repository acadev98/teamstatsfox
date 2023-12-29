package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Cards;

public interface CardsRepository extends PagingAndSortingRepository<Cards, Long>, JpaRepository<Cards, Long> {

	Optional<Cards> findTopByOrderByIdDesc();

	List<Cards> findAllByMatchId(Long id);

	List<Cards> findAllByPlayerId(Long id);
}