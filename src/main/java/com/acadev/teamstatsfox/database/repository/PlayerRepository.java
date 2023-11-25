package com.acadev.teamstatsfox.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.acadev.teamstatsfox.database.entity.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long>, JpaRepository<Player, Long> {
	List<Player> findByName(@Param("name") String name);
}