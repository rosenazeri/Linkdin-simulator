package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class Profile2 {
    User user;
    @FXML
    Button background;
    @FXML
    Button back;
    @FXML
    Button certainJobButton ;

    @FXML
    Button next;

    @FXML
    Button educationButton;
    @FXML
    Button connection;
    @FXML
    ImageView Background ;
    @FXML
    Label Biography ;

    @FXML
    Label countryLabel;

    @FXML
    Label cityLabel;
    @FXML
    TextField BiographyTextField ;
    @FXML
    TextField countryTextField;

    @FXML
    TextField cityTextField;

    @FXML
    Label industryLabel;

    @FXML
    TextField industryTextField;

    public static ProfileModel profile;

   private void initialize() {
        // Initialization code goes here
    }
    @FXML
    private void backButtonClicked(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void AddBackground(ActionEvent event) {
        // Code to handle the add background button click event
    }
    @FXML
    private void nextButtonClicked(ActionEvent event) {
        if (BiographyTextField.getText().length() > 220) {
           BiographyTextField.setStyle("-fx-border-color: red;");
            return;
        }
        else
            BiographyTextField.setStyle("");
        if (countryTextField.getText().length() > 60) {
            countryTextField.setStyle("-fx-border-color: red;");
            return;
        }
        else
            countryTextField.setStyle("");
        if (cityTextField.getText().length() > 60) {
            cityTextField.setStyle("-fx-border-color: red;");
            return;
        }
        else
            cityTextField.setStyle("");
        if (industryTextField.getText().length() > 60){
            industryTextField.setStyle("-fx-border-color: red;");
            return ;
        }
        else
            industryLabel.setStyle("");

        profile.setBiography(Biography.getText());
        profile.setCountry(countryTextField.getText());
        profile.setCity(cityTextField.getText());


        {

            String response;

            try {
                URL url = new URL("http://localhost:8080/profile");
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

                //User user1 = new User(UserName.getText(),firstName.getText(),email.getText(),lastName.getText(),password1.getText(),null);

                //Gson gson = new Gson();

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(profile);
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
                        m.changeScene(4);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // profile completed !!
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
    @FXML
    private void certainJobButtonClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CertainJob.fxml"));
            AnchorPane certainJobPage = loader.load();
            CertainJob controller = loader.getController();
            Scene scene = new Scene(certainJobPage);
            Stage currentStage = (Stage) certainJobButton.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void educationButtonClicked(ActionEvent actionEvent) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Education.fxml"));
//            AnchorPane Profile2Page = loader.load();
//            Educations controller = loader.getController();
//            controller.setUser(user);
//            Stage stage = (Stage) educationButton.getScene().getWindow();
//            Scene scene = new Scene(Profile2Page);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    private void connectionButtonClicked(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Connection.fxml"));
//            AnchorPane Profile2Page = loader.load();
//            Connections controller = loader.getController();
//            controller.setUser(user);
//            Stage stage = (Stage) certainJobButton.getScene().getWindow();
//            Scene scene = new Scene(Profile2Page);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}