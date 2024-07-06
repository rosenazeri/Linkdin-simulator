package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;



public class SignUpController {
    @FXML
    Button back;
    @FXML
    TextField UserName ;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField email;
    @FXML
    PasswordField password1;
    @FXML
    PasswordField password2;
    @FXML
    Label firstname;
    @FXML
    Button done;

    private List<User> userData = new ArrayList<>();

    @FXML
    public void initialize() {
        // Initialization code here
    }

    @FXML
    public void handleDone(ActionEvent actionEvent) {
        try {
            String response;
            User createdUser;

            //GET request
            {
                URL url = new URL("http://localhost:8080/user");
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
                connection.setRequestMethod("GET");
//                int responseCode = connection.getResponseCode();
//                System.out.println(responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input;
                StringBuffer resp = new StringBuffer();
                while ((input = in.readLine()) != null) {
                    resp.append(input);
                }
                in.close();
                response = resp.toString();
                //response = response.substring(1);

                if (!response.equals("[]")) {

                    JSONArray jsonObj = new JSONArray(response);
                    String[] users = toStringArray(jsonObj);

                    for (String str : users) {
                        JSONObject obj = new JSONObject(str);
                        User user = new User(obj.getString("id"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("email"), obj.getString("password"), obj.getString("recovery-string"));
                        userData.add(user);

                    }

                }

            }

            if (!isValidInput()) {

            } else {
                User user = new User(UserName.getText(), firstName.getText(), lastName.getText(), email.getText(), password1.getText(), "null");

                boolean isDuplicateEmail = false;
                for (User existingUser : userData) {
                    if (existingUser.getEmail().equals(user.getEmail())) {
                        isDuplicateEmail = true;
                        break;
                    }
                }
                if (isDuplicateEmail) {
                    email.setStyle("-fx-border-color: red;");
                } else {
                    email.setStyle("");

                    // POST method

                    {
                        URL url = new URL("http://localhost:8080/user");
                        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

                        //User user1 = new User(UserName.getText(),firstName.getText(),email.getText(),lastName.getText(),password1.getText(),null);

                        Gson gson = new Gson();

                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(user);
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
                                HelloApplication.currentUser = user;
                                m.changeScene(4);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // signed up !!
                        } else {
                            // server error !!
                        }


                    }


                }
            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }

        // post
    }
        private boolean isValidInput() {
        if (UserName.getText().isEmpty()) {
            UserName.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            UserName.setStyle("");
        if (firstName.getText().isEmpty()) {
            firstName.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            firstName.setStyle("");
        if (firstName.getText().length() > 20 || !isValidNameAndLastname(firstName.getText())) {
            firstname.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            firstName.setStyle("");
        if (lastName.getText().isEmpty()) {
            lastName.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            lastName.setStyle("");
        if (lastName.getText().length() > 40 || !isValidNameAndLastname(lastName.getText())) {
            lastName.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            lastName.setStyle("");
        if (email.getText().isEmpty()) {
            email.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            email.setStyle("");
        if (!isValidEmailAddress(email.getText())) {
            email.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            email.setStyle("");
        if (password1.getText().isEmpty() || password1.getText().length() < 8) {
           password1.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            password1.setStyle("");

        if (!(password1.getText().equals(password2.getText()))) {
            password2.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            password2.setStyle("");
        if (password2.getText().isEmpty()) {
            password2.setStyle("-fx-border-color: red;");
            return false;
        }
        else
            password2.setStyle("");
        return true;
    }

    public void handleBackButtonAction(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean isValidEmailAddress(String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        return valid;
    }
    public static boolean isValidNameAndLastname(String name){
        return name.matches("[a-zA-Z]+");
    }

    public static String[] toStringArray(JSONArray array) {
        if(array == null)
            return new String[0];

        String[] arr = new String[array.length()];
        for(int i = 0; i < arr.length; i++)
            arr[i] = array.optString(i);
        return arr;
    }



}
