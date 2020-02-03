package com.kat.photohost.gui;

import com.kat.photohost.ImageUploader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("uploadImage")
public class UploadGui extends VerticalLayout {

    private ImageUploader imageUploader;

    @Autowired
    public UploadGui(ImageUploader imageUploader) {

        Label label = new Label();

        TextField textField = new TextField();
        Button button = new Button("Upload");
        button.addClickListener(clickEvent -> {


            String picture = imageUploader.uploadFile(textField.getValue());
            Image image = new Image(picture, "no picture!");
            label.setText("Upload successful!");
            add(label);
            add(image);
        });

        add(textField);
        add(button);
    }
}
