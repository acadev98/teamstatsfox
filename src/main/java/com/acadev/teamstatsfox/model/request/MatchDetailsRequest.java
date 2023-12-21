package com.acadev.teamstatsfox.model.request;

import lombok.Data;

@Data
public class MatchDetailsRequest {
	private String username;
	private String email;
	private String password;
}