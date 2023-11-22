package com.acadev.teamstatsfox.model.response;

import com.acadev.teamstatsfox.database.entity.Player;

import lombok.Data;

@Data
public class PlayerDTOResponse extends Player {

	private Integer age;
	private Integer matches;
	private Integer goals;
	private Integer assists;
	private Integer captains;
	private Integer redCards;
	private Integer yellowCards;

}
