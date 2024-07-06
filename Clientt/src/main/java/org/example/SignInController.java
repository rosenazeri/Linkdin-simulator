package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SignInController {
    @FXML
    Button back;
    @FXML
    Button signInButton;
    @FXML
    TextField emailField;
    @FXML
    PasswordField passwordField;

    User foundedUserByID;
    public void initialize() {
        // Initialization code goes here
    }
    @FXML
    private void handleSignInAction(ActionEvent actionEvent) {

        if(emailField.getText().isEmpty()){

        }

        try{
            URL url = new URL("http://localhost:8080/login/" + emailField.getText() + "/" + passwordField.getText()); //sending username and password to server

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input; // response from server
            StringBuffer resp = new StringBuffer();
            while ((input = in.readLine()) != null) {
                resp.append(input);
            }
            in.close();

            String response = resp.toString();

            if (response.equals("ok")){
                url = new URL("http://localhost:8080/user/" + emailField.getText());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                resp = new StringBuffer();
                while ((input = in.readLine()) != null) {
                    resp.append(input);
                }
                in.close();

                response = resp.toString();

                JSONObject obj = new JSONObject(response);

                clear();

                HelloApplication m = new HelloApplication();
                try {
                    HelloApplication.currentUser = new User(obj.getString("id"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("email"), obj.getString("password"), obj.getString("recovery-string"));
                    m.changeScene(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // toDo: lets go to home page
            } else {
                emailField.setStyle("-fx-border-color: red;");
                passwordField.setStyle("-fx-border-color: red;");
                // toDo: set label false user or pass
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void handleBackButtonAction(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(1);
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void clear() {
        emailField.setText("");
        passwordField.setText("");
    }
}