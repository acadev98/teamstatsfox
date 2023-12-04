package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.service.PublicService;
import com.acadev.teamstatsfox.service.impl.UserDetailsImpl;

@RestController
@RequestMapping("/api/public")
public class PublicController {

	@Autowired
	private PublicService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echo() {
		return service.echo();
	}

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public UserDetailsImpl profile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails;
    }

	@GetMapping("/statsFromStaticData")
	public ResponseEntity<Object> getDataStatic() {
		return service.getDataStatic();
	}

	@GetMapping("/statsFromCvs")
	public ResponseEntity<Object> getDataCvs() {
		return service.getDataCvs();
	}

	@GetMapping("/players")
	public ResponseEntity<Object> getPlayers() {
		return service.getPlayers();
	}

}
