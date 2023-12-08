package com.acadev.teamstatsfox.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class GoalDTO {
	private Long id;
	private Long matchId;
	private Long playerId;
	private Boolean our;
	private Boolean penalty;
	private Integer minute;
}
