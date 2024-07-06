package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ResourceBundle;

public class CertainJob2 {
      User user;

    @FXML
      Button Back;

    @FXML
      TextArea Explanation;

    @FXML
      TextArea skills;
    @FXML
      Button Done;

    public static CertainJobs certainJobs;
    @FXML
    private void initialize() {
        // Initialization code goes here
    }

    @FXML
    private void handleBackButtonAction(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDoneButtonAction(ActionEvent actionEvent) {
        if (Explanation.getText().length() > 1000){
            Explanation.setStyle("-fx-border-color: red;");
            return ;
        }
        else
            Explanation.setStyle("");

        {

            String response;

            try {
                URL url = new URL("http://localhost:8080/jobs");
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

                //User user1 = new User(UserName.getText(),firstName.getText(),email.getText(),lastName.getText(),password1.getText(),null);

                //Gson gson = new Gson();

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(certainJobs);
                //String json = gson.toJson(user);
                byte[] jsonBytes = json.getBytes();

                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.getOutputStream().write(jsonBytes);

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                for(int c; (c = in.read()) > 0;){
                    stringBuilder.append((char) c);
                }
                response = stringBuilder.toString();

                if (response.equals("ok")){
                    HelloApplication m = new HelloApplication();
                    try {
                        m.changeScene(6);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // job sent !!
                } else {
                    // server error !!
                }
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}