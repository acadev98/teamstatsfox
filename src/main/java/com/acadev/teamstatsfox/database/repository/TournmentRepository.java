package com.acadev.teamstatsfox.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Tournment;

public interface TournmentRepository extends PagingAndSortingRepository<Tournment, Long>, JpaRepository<Tournment, Long> {

	Optional<Tournment> findTopByOrderByIdDesc();
}