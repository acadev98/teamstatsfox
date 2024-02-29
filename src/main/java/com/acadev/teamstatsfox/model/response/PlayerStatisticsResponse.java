package com.acadev.teamstatsfox.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStatisticsResponse {
	private Long id;
	private String player;
	private Integer matches;
	private Integer goals;
	private Integer assists;
	private Integer captains;
	private Integer yellowCards;
	private Integer redCards;
	private Boolean active;
}
