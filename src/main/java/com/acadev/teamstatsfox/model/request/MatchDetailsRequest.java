package com.acadev.teamstatsfox.model.request;

import java.util.List;

import lombok.Data;

@Data
public class MatchDetailsRequest {
	private MatchRequest match;
	private Integer captain;
	private List<CardRequest> cards;
	private List<GoalRequest> goals;
	private List<PresentRequest> presents;
}