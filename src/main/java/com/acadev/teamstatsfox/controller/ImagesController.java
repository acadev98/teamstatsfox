package com.acadev.teamstatsfox.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.ImagesService;

@RestController
@RequestMapping("/images")
public class ImagesController {

	@Autowired
	private ImagesService service;

	@GetMapping("/{name}")
	public ResponseEntity<Object> getImageByName(@PathVariable("name") String name) {
		return service.getImage(name, false);
	}

	@GetMapping("/{name}/default")
	public ResponseEntity<Object> getImageByNameOrDefault(@PathVariable("name") String name) {
		return service.getImage(name, true);
	}

	@GetMapping("/info/{name}")
	public ResponseEntity<Object> getImageInfoByName(@PathVariable("name") String name) {
		return ResponseHandler.generateResponse("", HttpStatus.OK, service.getInfoByImageByName(name));
	}

	@PostMapping
	public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile file,
			@RequestParam("name") String name) throws IOException {
		return ResponseHandler.generateResponse(service.uploadImage(file, name), HttpStatus.OK);
	}

}