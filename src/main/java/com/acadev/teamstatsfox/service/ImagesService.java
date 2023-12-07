package com.acadev.teamstatsfox.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.database.entity.Images;

public interface ImagesService {

	ResponseEntity<Object> getImage(String name, boolean useDefault);

	Images getInfoByImageByName(String name);

	String uploadImage(MultipartFile file, String name) throws IOException;

}
