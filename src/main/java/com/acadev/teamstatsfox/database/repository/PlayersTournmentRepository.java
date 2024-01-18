package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.PlayersTournment;

public interface PlayersTournmentRepository extends PagingAndSortingRepository<PlayersTournment, Long>, JpaRepository<PlayersTournment, Long> {
	Optional<PlayersTournment> findTopByOrderByIdDesc();
	List<PlayersTournment> findAllByTournmentId(Long id);
	List<PlayersTournment> findAllByPlayerId(Long id);
}