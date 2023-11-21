package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.service.PublicService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private PublicService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echo() {
		return service.echo();
	}

	@GetMapping("/statsFromStaticData")
	public ResponseEntity<Object> getDataStatic() {
		return service.getDataStatic();
	}

	@GetMapping("/statsFromCvs")
	public ResponseEntity<Object> getDataCvs() {
		return service.getDataCvs();
	}

}
