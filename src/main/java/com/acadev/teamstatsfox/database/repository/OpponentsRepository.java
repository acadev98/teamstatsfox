package com.acadev.teamstatsfox.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Opponents;

public interface OpponentsRepository extends PagingAndSortingRepository<Opponents, Long>, JpaRepository<Opponents, Long> {

	Optional<Opponents> findTopByOrderByIdDesc();

	Optional<Opponents> findByName(String name);
}