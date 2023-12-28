package com.acadev.teamstatsfox.model.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MatchRequest {
	private LocalDate date;
	private String time;
	private String opponent;
	private String tournment;
	private String resume;
}
