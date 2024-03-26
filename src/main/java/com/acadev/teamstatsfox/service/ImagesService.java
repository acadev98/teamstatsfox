package com.acadev.teamstatsfox.service;

import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.database.entity.Images;

public interface ImagesService {

	Images save(String imageName, MultipartFile file);
	
	Images getImageByName(String imageName);

}
