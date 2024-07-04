package com.bouncy_mehdich.Client.Controllers;

import com.bouncy_mehdich.Client.Models.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class PostController {

    String userID; // get this from the main class
    String postCaption;
    String postID; // generating post ID

    public void post(){

        try{
            String response;
            URL url = new URL("http://localhost:8080/post");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Post post = new Post(postID, postCaption, userID, new Date(System.currentTimeMillis()));

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


}
