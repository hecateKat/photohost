package com.kat.photohost;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kat.photohost.entity.Image;
import com.kat.photohost.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;

    private ImageRepository imageRepository;

    @Autowired
    public ImageUploader(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpeqofggk",
                "api_key", "928139493653883",
                "api_secret", "UPVgugtX52bsiUXXwKE6UTLkusc"));

    }

    public String uploadAndSaveFile(String path){
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepository.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
//            e.printStackTrace();
        }

        return uploadResult.get("url").toString();
    }
}


