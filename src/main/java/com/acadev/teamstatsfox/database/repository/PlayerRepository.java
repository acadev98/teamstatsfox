package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.acadev.teamstatsfox.database.entity.Players;

public interface PlayerRepository extends PagingAndSortingRepository<Players, Long>, JpaRepository<Players, Long> {
	List<Players> findByName(@Param("name") String name);
	Optional<Players> findTopByOrderByIdDesc();
}