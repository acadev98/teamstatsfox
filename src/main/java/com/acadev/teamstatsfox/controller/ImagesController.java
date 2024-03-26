package com.acadev.teamstatsfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.database.entity.Images;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.ImagesService;
import com.acadev.teamstatsfox.utils.MessagesUtils;

@RestController
@RequestMapping("/api/images")
public class ImagesController {
	

	@Autowired
	private ImagesService service;

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getImageByPlayerId(@PathVariable("id") Long id) {
		Images image = service.getImageByName(id.toString());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(image.getType()))
				.body(image.getData());
	}

	@PostMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> saveImageByPlayerId(@PathVariable("id") Long id,
			@RequestParam("file") MultipartFile file) {
		return ResponseHandler.generateResponse(MessagesUtils.PLAYER_IMAGE_CREATED, HttpStatus.CREATED,
				service.save(id.toString(), file));
	}
	
}
