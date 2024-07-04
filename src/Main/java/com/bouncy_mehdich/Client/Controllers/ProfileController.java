package com.bouncy_mehdich.Client.Controllers;

import com.bouncy_mehdich.Client.Models.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class ProfileController {

    String userID; // get this from the main class

    String nickName;
    String bio;
    String country;
    String city;
    String herfe;
    Date birth;

    public void completeProfile(){

        String response;
        try {
            URL url = new URL("http://localhost:8080/profile/" + userID);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Profile profile = new Profile(userID, nickName, bio, country, city, herfe, birth);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(profile);
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
                // profile completed !!
            } else {
                // server error !!
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProfile(){
        String response;
        try {
            URL url = new URL("http://localhost:8080/profile/" + userID);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Profile profile = new Profile(userID, nickName, bio, country, city, herfe, birth);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(profile);
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
                // profile completed !!
            } else {
                // server error !!
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
