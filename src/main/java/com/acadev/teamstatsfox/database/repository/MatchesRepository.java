package com.acadev.teamstatsfox.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Matches;

public interface MatchesRepository extends PagingAndSortingRepository<Matches, Long>, JpaRepository<Matches, Long> {

	Optional<Matches> findTopByOrderByIdDesc();
}