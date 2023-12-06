package com.acadev.teamstatsfox.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GamesPlayedResponse {
	private Long id;
	private Integer position;
	private String name;
    private Integer matches;
}
