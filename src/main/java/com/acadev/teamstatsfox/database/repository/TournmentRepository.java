package com.acadev.teamstatsfox.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Tournments;

public interface TournmentRepository extends PagingAndSortingRepository<Tournments, Long>, JpaRepository<Tournments, Long> {

	Optional<Tournments> findTopByOrderByIdDesc();
}