package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WelcomePage {
    @FXML
      Label welcome;

    @FXML
      Button SignIn;

    @FXML
      Button SignUp;

    @FXML
    public void handleSignInAction(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleSignUpAction(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void replaceTextInFXML(String filename, String oldText, String newText) {
        //change color
        try {
            filename = filename.trim();
            Path filePath = Paths.get(filename);
            if (!Files.exists(filePath)) {
                throw new IOException("File not found: " + filename);
            }
            String content = new String(Files.readAllBytes(filePath));
            content = content.replaceAll(oldText, newText);
            FileWriter writer = new FileWriter(filename);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DarkHandler(ActionEvent actionEvent) {
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Hello.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Sign-in.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Sign-up.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home2.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\CertainJob.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\CertainJob2.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Connection.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Connection2.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Education.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Education2.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Post.fxml", "#cec8ff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Profile.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Profile2.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Search.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ShowProfile.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\UserProfiles.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\src\main\resources\org\example\\UserProfiles.fxml", "#cec8ff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ShowProfile.fxml", "#cec8ff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Search.fxml", "#e2dfff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home2.fxml", "#cec8ff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home2.fxml", "#ebe8ff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#cec8ff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#dedeff" , "#3f3fff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#ffffff" , "#000000");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\commentView.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\commentView.fxml", "#dedeff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\commentView.fxml", "#ffffff" , "#000000");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\LikeView.fxml", "#bfb8ff" , "#03001b");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\LikeView.fxml", "#dedeff" , "#4530ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\LikeView.fxml", "#ffffff" , "#000000");

    }
    public void LightHandler(ActionEvent actionEvent) {
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Hello.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Sign-in.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Sign-up.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home2.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\CertainJob.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\CertainJob2.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Connection.fxml" , "#03001b", "#bfb8ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Connection2.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Education.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Education2.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Post.fxml", "#4530ff", "#cec8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Profile.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Profile2.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Search.fxml" , "#03001b", "#bfb8ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ShowProfile.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\UserProfiles.fxml", "#03001b", "#bfb8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\UserProfiles.fxml", "#4530ff", "#cec8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ShowProfile.fxml" , "#4530ff", "#cec8ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Search.fxml", "#4530ff", "#e2dfff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home2.fxml" , "#4530ff", "#cec8ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\Home2.fxml", "#4530ff", "#ebe8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#4530ff", "#cec8ff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#3f3fff", "#dedeff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\ChatRoom.fxml", "#000000", "#ffffff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\commentView.fxml" , "#03001b", "#bfb8ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\commentView.fxml", "#4530ff", "#dedeff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\commentView.fxml" , "#000000", "#ffffff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\LikeView.fxml" , "#03001b", "#bfb8ff");
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\LikeView.fxml", "#4530ff", "#dedeff" );
        replaceTextInFXML("Clientt\\src\\main\\resources\\org\\example\\LikeView.fxml" , "#000000", "#ffffff");

    }
}