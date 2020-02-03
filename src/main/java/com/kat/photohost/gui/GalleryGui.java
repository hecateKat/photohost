package com.kat.photohost.gui;

import com.kat.photohost.ImageUploader;
import com.kat.photohost.entity.Image;
import com.kat.photohost.repository.ImageRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private ImageRepository imageRepository;

    @Autowired
    public GalleryGui(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;

        List<Image> all = imageRepository.findAll();

        all.stream().forEach(e -> {
            com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(e.getImageUrl(), "null");
            add(image);
        });

    }
}
