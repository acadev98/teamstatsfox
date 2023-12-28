package com.acadev.teamstatsfox.model.request;

import com.acadev.teamstatsfox.utils.enums.EGoalType;

import lombok.Data;

@Data
public class GoalRequest {
	private String minute;
	private String playerId;
	private String assistPlayerId;
	private EGoalType type;
	private Boolean our;
}
