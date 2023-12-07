package com.acadev.teamstatsfox.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssistsPlayedResponse {
	private Long id;
	private Integer position;
	private String name;
	private Integer assists;
}
