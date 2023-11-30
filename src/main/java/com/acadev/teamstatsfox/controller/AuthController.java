package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.database.entity.User;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.request.LoginRequest;
import com.acadev.teamstatsfox.security.jwt.JwtUtil;
import com.acadev.teamstatsfox.service.UserService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;

	}

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody User user) {
		return ResponseHandler.generateResponse(MessagesUtils.USER_CREATED, HttpStatus.CREATED, service.create(user));
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest user) {
		return ResponseHandler.generateResponse(MessagesUtils.USER_LOGED, HttpStatus.OK, service.login(user));
	}

}
