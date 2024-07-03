package com.bouncy_mehdich.Client.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginController {
    String userID = "";
    String pass = "";

    public void login(){
        if(userID.length() == 0 || pass.length() == 0){
            return; //checking is username or password is null or not
        }
        try{
            URL url = new URL("http://localhost:8080/login/" + userID + "/" + pass); //sending username and password to server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input; // response from server
            StringBuffer resp = new StringBuffer();
            while ((input = in.readLine()) != null) {
                resp.append(input);
            }
            in.close();

            String response = resp.toString();

            if(response.equals("ok")){

            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
