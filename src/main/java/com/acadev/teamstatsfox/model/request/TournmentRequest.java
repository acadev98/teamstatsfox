package com.acadev.teamstatsfox.model.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TournmentRequest {
	private LocalDate startDate;
	private String name;
	private String description;
	private Boolean active;
}
