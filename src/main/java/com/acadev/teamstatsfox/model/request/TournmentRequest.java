package com.acadev.teamstatsfox.model.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TournmentRequest {
	private LocalDate date;
	private String name;
	private String description;
	private Boolean active;
}
