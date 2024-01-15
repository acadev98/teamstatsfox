package com.acadev.teamstatsfox.service.impl;

import java.time.LocalDate;
import java.time.Period;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Cards;
import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.PlayersTourment;
import com.acadev.teamstatsfox.database.entity.Presents;
import com.acadev.teamstatsfox.model.response.AssistsPlayedResponse;
import com.acadev.teamstatsfox.model.response.GamesPlayedResponse;
import com.acadev.teamstatsfox.model.response.GoalsPlayedResponse;
import com.acadev.teamstatsfox.model.response.PlayerStatsFromCvs;
import com.acadev.teamstatsfox.model.response.PlayerStatsResponse;

@Service
public class MapperService {

	@Autowired
	private ModelMapper modelMapper;

	public PlayerStatsResponse convertToDto(Players player) {
		PlayerStatsResponse response = modelMapper.map(player, PlayerStatsResponse.class);

		if (null != player.getBirthday()) {
			response.setAge(Period.between(player.getBirthday(), LocalDate.now()).getYears());
		}

		return response;
	}

	public AssistsPlayedResponse convertToDtoAssists(PlayerStatsFromCvs player) {
		AssistsPlayedResponse response = AssistsPlayedResponse.builder().assists(player.getAssists())
				.name(player.getName()).build();
		return response;
	}

	public GamesPlayedResponse convertToDtoGames(PlayerStatsFromCvs player) {
		GamesPlayedResponse response = GamesPlayedResponse.builder().matches(player.getMatches()).name(player.getName())
				.build();
		return response;
	}

	public GoalsPlayedResponse convertToDtoGoals(PlayerStatsFromCvs player) {
		GoalsPlayedResponse response = GoalsPlayedResponse.builder().goals(player.getGoals()).name(player.getName())
				.build();
		return response;
	}

	public Long getPlayerIds(Presents presents) {
		return presents.getPlayerId();
	}

	public Long getPlayerIds(PlayersTourment playersTournments) {
		return playersTournments.getPlayerId();
	}

	public Long getPlayerIds(Cards cards) {
		return cards.getPlayerId();
	}

}
