package com.acadev.teamstatsfox.service;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

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

	List<Integer> availableNumbers();

	Boolean saveImage(Long id, MultipartFile file);

	Resource getImageByPlayerId(Long id) throws MalformedURLException;

}
