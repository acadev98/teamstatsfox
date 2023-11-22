package com.acadev.teamstatsfox.service.impl;

import java.time.LocalDate;
import java.time.Period;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Player;
import com.acadev.teamstatsfox.model.response.PlayerDTOResponse;

@Service
public class MapperService {

	@Autowired
	private ModelMapper modelMapper;

	public PlayerDTOResponse convertToDto(Player player) {
		PlayerDTOResponse playerDTO = modelMapper.map(player, PlayerDTOResponse.class);

		if (null != player.getBirthday()) {
			playerDTO.setAge(Period.between(player.getBirthday(), LocalDate.now()).getYears());
		}

		return playerDTO;
	}

}
