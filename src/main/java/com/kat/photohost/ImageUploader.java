package com.kat.photohost;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageUploader {

    private Cloudinary cloudinary;

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
            e.printStackTrace();
        }

        return "";
    }


    public static void main(String[] args) throws IOException {

    }
}


