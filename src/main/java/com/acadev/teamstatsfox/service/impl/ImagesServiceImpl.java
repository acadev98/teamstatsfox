package com.acadev.teamstatsfox.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.database.entity.Images;
import com.acadev.teamstatsfox.database.repository.ImageDataRepository;
import com.acadev.teamstatsfox.handler.ResponseHandler;
import com.acadev.teamstatsfox.service.ImagesService;
import com.acadev.teamstatsfox.utils.ConstantsUtils;
import com.acadev.teamstatsfox.utils.ImagesUtils;
import com.acadev.teamstatsfox.utils.MessagesUtils;

import jakarta.transaction.Transactional;

@Service
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	private ImageDataRepository imageDataRepository;

	@Transactional
	public ResponseEntity<Object> getImage(String name, boolean useDefault) {

		Optional<Images> dbImage = imageDataRepository.findByName(name);

		if (dbImage.isEmpty()) {
			if (useDefault) {
				dbImage = imageDataRepository.findByName(ConstantsUtils.IMAGE_NOT_FOUND);
			} else {
				return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, MessagesUtils.RESULT_NOT_FOUND);
			}
		}

		byte[] image = ImagesUtils.decompressImage(dbImage.get().getImageData());

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "image/png");

		return ResponseHandler.generateResponse("", HttpStatus.OK, image, headers);
	}

	@Transactional
	public Images getInfoByImageByName(String name) {
		Optional<Images> dbImage = imageDataRepository.findByName(name);

		return Images.builder().name(dbImage.get().getName()).type(dbImage.get().getType())
				.imageData(ImagesUtils.decompressImage(dbImage.get().getImageData())).build();

	}

	public String uploadImage(MultipartFile file, String name) throws IOException {

		imageDataRepository.save(Images.builder().name(name).type(file.getContentType())
				.imageData(ImagesUtils.compressImage(file.getBytes())).build());

		return file.getOriginalFilename();

	}

}