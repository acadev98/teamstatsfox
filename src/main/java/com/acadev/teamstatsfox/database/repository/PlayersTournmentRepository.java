package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.PlayersTourment;

public interface PlayersTournmentRepository extends PagingAndSortingRepository<PlayersTourment, Long>, JpaRepository<PlayersTourment, Long> {
	Optional<PlayersTourment> findTopByOrderByIdDesc();
	List<PlayersTourment> findAllByTournmentId(Long id);
	List<PlayersTourment> findAllByPlayerId(Long id);
}