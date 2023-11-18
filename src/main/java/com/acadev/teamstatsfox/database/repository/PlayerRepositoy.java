package com.acadev.teamstatsfox.database.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acadev.teamstatsfox.database.entity.Player;

@Repository
public interface PlayerRepositoy extends PagingAndSortingRepository<Player, Long>, CrudRepository<Player, Long> {
	List<Player> findByName(@Param("name") String name);
}