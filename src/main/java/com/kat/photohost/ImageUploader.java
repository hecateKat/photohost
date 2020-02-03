package com.kat.photohost;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;

    @Autowired
    public ImageUploader() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpeqofggk",
                "api_key", "928139493653883",
                "api_secret", "UPVgugtX52bsiUXXwKE6UTLkusc"));

    }

    public String uploadFile(String path){
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
//            e.printStackTrace();
        }

        return uploadResult.get("url").toString();
    }
}


