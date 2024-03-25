package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.OpponentsTournment;

public interface OpponentsTournmentRepository
		extends PagingAndSortingRepository<OpponentsTournment, Long>, JpaRepository<OpponentsTournment, Long> {
	Optional<OpponentsTournment> findTopByOrderByIdDesc();

	List<OpponentsTournment> findAllByTournmentId(Long id);

	List<OpponentsTournment> findAllByOpponentId(Long id);
}