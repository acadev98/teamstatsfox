package com.acadev.teamstatsfox.model.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MatchRequest {
	private LocalDate date;
	private String time;
	private Long opponent;
	private Long tournment;
	private String resume;
}
