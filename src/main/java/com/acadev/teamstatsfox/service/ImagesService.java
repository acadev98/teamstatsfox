package com.acadev.teamstatsfox.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.database.entity.Images;

public interface ImagesService {

	String uploadImage(MultipartFile file, String name) throws IOException;

	Images getInfoByImageByName(String name);

	ResponseEntity<Object> getImage(String name, boolean useDefault);

}
