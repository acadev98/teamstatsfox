package com.acadev.teamstatsfox.model.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
	private Long id;
	private String username;
	private String email;
    private String token;
    private String type;
    private List<String> roles;
}
