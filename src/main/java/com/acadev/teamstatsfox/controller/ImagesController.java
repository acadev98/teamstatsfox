package com.acadev.teamstatsfox.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.impl.ImagesService;

@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    private ImagesService service;

    @PostMapping
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("name") String name) throws IOException {
    	return ResponseHandler.generateResponse(service.uploadImage(file, name), HttpStatus.OK);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<Object>  getImageInfoByName(@PathVariable("name") String name){
    	return ResponseHandler.generateResponse(service.getInfoByImageByName(name), HttpStatus.OK);	
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object>  getImageByName(@PathVariable("name") String name){
    	
    	MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    	headers.add("Content-Type", "image/png");
		
        return ResponseHandler.generateResponse(service.getImage(name), headers, HttpStatus.OK);
    }


}