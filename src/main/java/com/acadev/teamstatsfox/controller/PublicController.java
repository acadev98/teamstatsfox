package com.acadev.teamstatsfox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.PublicService;
import com.acadev.teamstatsfox.service.impl.UserDetailsImpl;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@RestController
@RequestMapping("/api/public")
public class PublicController {

	@Autowired
	private PublicService service;

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@GetMapping("/echo")
	public ResponseEntity<Object> echo() {
		return service.echo();
	}

	@GetMapping("/statsFromCvs")
	public ResponseEntity<Object> getDataCvs() {
		return service.getDataCvs();
	}

	@GetMapping("/statsFromStaticData")
	public ResponseEntity<Object> getDataStatic() {
		return service.getDataStatic();
	}

	@GetMapping("/players")
	public ResponseEntity<Object> getPlayers() {
		return service.getPlayers();
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/profile")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public UserDetailsImpl profile() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		return userDetails;
	}

	@GetMapping("/top/assists")
	public ResponseEntity<Object> topAssists() {
		return ResponseHandler.generateResponse(MessagesUtils.TOP_10_PLAYED_ASSISTS, HttpStatus.OK,
				service.topAssists());
	}

	@GetMapping("/top/games")
	public ResponseEntity<Object> topGames() {
		return ResponseHandler.generateResponse(MessagesUtils.TOP_10_PLAYED_MATCHS, HttpStatus.OK, service.topGames());
	}

	@GetMapping("/top/goals")
	public ResponseEntity<Object> topGoals() {
		return ResponseHandler.generateResponse(MessagesUtils.TOP_10_PLAYED_GOALS, HttpStatus.OK, service.topGoals());
	}

	@GetMapping("/numbers/avaibles")
	public ResponseEntity<Object> availableNumbers() {
		return ResponseHandler.generateResponse(MessagesUtils.AVAILABLE_NUMBERS, HttpStatus.OK, service.availableNumbers());
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

}
