package com.acadev.teamstatsfox.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatsFromCvs {
	private String name;
	private Integer matches;
	private Integer goals;
	private Integer assists;
	private Integer redCard;
	private Integer yellowCard;
	private Integer captains;
}
