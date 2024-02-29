package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.model.request.PlayerRequest;
import com.acadev.teamstatsfox.model.response.PlayerStatisticsResponse;
import com.acadev.teamstatsfox.model.response.PlayersDetailsResponse;
import com.acadev.teamstatsfox.model.response.PrevAndNextPlayersResponse;

public interface PlayerService {

	Players create(PlayerRequest player);

	Players delete(Long id);

	String echo();

	Players getPlayer(Long id);

	List<Players> getPlayers();

	List<Players> getPlayersActives();

	Players update(Long id, PlayerRequest player);

	List<Integer> findNumbers();

	PlayersDetailsResponse getPlayerDetails(Long id);

	PrevAndNextPlayersResponse getPlayerPrevAndNext(Long id);

	List<PlayerStatisticsResponse> getPlayersStatistics();

}
