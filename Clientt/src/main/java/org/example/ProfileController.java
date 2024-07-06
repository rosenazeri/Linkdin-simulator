package org.example;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileController {

    @FXML
    Button Back;
    @FXML
    AnchorPane root;

    @FXML
    Label nameLabel;

    @FXML
    TextField firstName;

    @FXML
    TextField lastName;

    @FXML
    TextField additionalName;

    @FXML
    TextField email;

    @FXML
    Button image;

    @FXML
    ImageView imageData;

    @FXML
    Button nextButton;

    @FXML
    private void initialize() {
        // Initialization code goes here
    }

    @FXML
    private void handleImageButtonClick(ActionEvent actionEvent) {
        // Code to handle the image button click event
    }

    @FXML
    private void handleNextButtonClick(ActionEvent actionEvent) {
        String additionalNameText = additionalName.getText();
        if (additionalNameText.length() > 40) {
            additionalName.setStyle("-fx-border-color: red;");
            return;
        }
        else
            additionalName.setStyle("");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        ProfileModel profile = new ProfileModel(HelloApplication.currentUser.getID(), additionalName.getText(), null,null,null,null,null);

        try {
            Profile2.profile = profile;
            HelloApplication m = new HelloApplication();
            m.changeScene(6);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backHandler(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

