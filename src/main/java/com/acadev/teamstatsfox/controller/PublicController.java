package com.acadev.teamstatsfox.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acadev.teamstatsfox.service.impl.PublicService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private PublicService service;

	@GetMapping("/echo")
	public ResponseEntity<Object> echoTest() {
		return service.echoTest();
	}

	@GetMapping("/statsFromStaticData")
	public ResponseEntity<Object> statsFromStaticData() {
		return service.statsFromStaticData();
	}

	@GetMapping("/statsFromCvs")
	public ResponseEntity<Object> statsFromCvs() throws IOException {
		return service.statsFromCvs();
	}

}
