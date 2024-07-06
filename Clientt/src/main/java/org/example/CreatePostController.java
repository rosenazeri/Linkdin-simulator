package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CreatePostController {
    @FXML

    TextArea captionTextArea;

    @FXML
    MenuButton Status;

    @FXML
    Button media;

    @FXML
    ImageView photoImageView;

    @FXML
    Button post;


    @FXML
    private void BackHandler(ActionEvent event) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mediaHandler(ActionEvent event) {
        // Handle media upload action
    }

    @FXML
    private void postHandler(ActionEvent event) {
        try{
            String response;
            URL url = new URL("http://localhost:8080/post");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Post post = new Post(captionTextArea.getText(), HelloApplication.currentUser.getID(), null);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(post);
            byte[] jsonBytes = json.getBytes();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(jsonBytes);

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            for(int c; (c = in.read()) > 0;){
                stringBuilder.append((char) c);
            }
            response = stringBuilder.toString();

            if (response.equals("ok")){
                // post uploaded !!
            } else {
                // server error !!
            }

            // TODO : imagePath????


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void statusHandler(ActionEvent actionEvent) {
    }

    // Add other methods as needed for additional functionality
}
