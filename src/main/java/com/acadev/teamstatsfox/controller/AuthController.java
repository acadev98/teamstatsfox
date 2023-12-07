package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.model.request.LoginRequest;
import com.acadev.teamstatsfox.model.request.RegisterRequest;
import com.acadev.teamstatsfox.service.impl.AuthServiceImpl;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthServiceImpl service;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
		return ResponseHandler.generateResponse(MessagesUtils.USER_LOGED, HttpStatus.OK, service.login(loginRequest));
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) {
		return ResponseHandler.generateResponse(MessagesUtils.USER_CREATED, HttpStatus.CREATED,
				service.register(registerRequest));
	}

}
