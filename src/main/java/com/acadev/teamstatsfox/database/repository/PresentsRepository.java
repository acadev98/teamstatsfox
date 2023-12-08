package com.acadev.teamstatsfox.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.acadev.teamstatsfox.database.entity.Presents;

public interface PresentsRepository extends PagingAndSortingRepository<Presents, Long>, JpaRepository<Presents, Long> {
	Optional<Presents> findTopByOrderByIdDesc();
	List<Presents> findAllByMatchId(Long id);
}