package com.acadev.teamstatsfox.service.impl;

import java.time.LocalDate;
import java.time.Period;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.model.response.PlayerStatsResponse;

@Service
public class MapperService {

	@Autowired
	private ModelMapper modelMapper;

	public PlayerStatsResponse convertToDto(Player player) {
		PlayerStatsResponse playerDTO = modelMapper.map(player, PlayerStatsResponse.class);

		if (null != player.getBirthday()) {
			playerDTO.setAge(Period.between(player.getBirthday(), LocalDate.now()).getYears());
		}

		return playerDTO;
	}

}
