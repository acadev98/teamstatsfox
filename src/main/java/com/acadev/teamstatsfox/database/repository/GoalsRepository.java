package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Goals;

public interface GoalsRepository extends PagingAndSortingRepository<Goals, Long>, JpaRepository<Goals, Long> {

	Optional<Goals> findTopByOrderByIdDesc();

	List<Goals> findAllByMatchId(Long id);

	List<Goals> findAllByPlayerId(Long id);

	List<Goals> findAllByAssistPlayerId(Long id);
}