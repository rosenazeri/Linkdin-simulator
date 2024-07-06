package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CertainJob implements Initializable {
     User user;
    @FXML
     Label Title;
    @FXML
     Label employmentType;
    @FXML
     Label company;
    @FXML
     Label workPlace;
    @FXML
     Label TypeOfWorkPlace;
    @FXML
     Label start;
    @FXML
     Label finish;
    @FXML
     TextField jobtitleField;
    @FXML
     TextField companyField;
    @FXML
     TextField workPlaceField;
    @FXML
    TextField employmentField;
    @FXML
    TextField workPlaceTypeField;
    @FXML
     DatePicker startDatePicker;
    @FXML
     DatePicker endDatePicker;
    @FXML
     Button nextButton;
    @FXML
     Button backButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // You can initialize ComboBox options here if needed
    }

    @FXML
    private void NextButtonClicked(ActionEvent actionEvent) {
        if (jobtitleField.getText().length() > 40){
            jobtitleField.setStyle("-fx-border-color: red;");
            return ;
        }
        else
            jobtitleField.setStyle("");
        if (companyField.getText().length() > 40){
            companyField.setStyle("-fx-border-color: red;");
            return ;
        }
        else
            companyField.setStyle("");
        if (workPlaceField.getText().length() > 40){
            workPlaceField.setStyle("-fx-border-color: red;");
            return ;
        }
        else
            workPlaceField.setStyle("");
        if (startDatePicker == null) {
            startDatePicker.setStyle("-fx-border-color: red;");
            return;
        }
        else
            startDatePicker.setStyle("");
        if (endDatePicker == null) {
            endDatePicker.setStyle("-fx-border-color: red;");
            return;
        }
        else
            endDatePicker.setStyle("");

        CertainJobs certainJobs = new CertainJobs(jobtitleField.getText(),employmentField.getText(), companyField.getText(), workPlaceField.getText(), workPlaceTypeField.getText(), null,null,null,null);

        CertainJob2.certainJobs = certainJobs;



        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CertainJob2.fxml"));
            AnchorPane CertainJobPage = loader.load();
            CertainJob2 controller = loader.getController();
            Stage stage = (Stage) nextButton.getScene().getWindow();
            Scene scene = new Scene(CertainJobPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void BackButtonClicked(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}