package com.acadev.teamstatsfox.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.acadev.teamstatsfox.database.entity.Images;
import com.acadev.teamstatsfox.database.repository.ImageDataRepository;
import com.acadev.teamstatsfox.utils.ImageUtil;

import jakarta.transaction.Transactional;

@Service
public class ImagesService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    public String uploadImage(MultipartFile file, String name) throws IOException {

        imageDataRepository.save(Images.builder()
                .name(name)
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return file.getOriginalFilename();

    }

    @Transactional
    public Images getInfoByImageByName(String name) {
        Optional<Images> dbImage = imageDataRepository.findByName(name);

        return Images.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<Images> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }


}