package com.bouncy_mehdich.Client.Controllers;

import com.bouncy_mehdich.Client.Models.Comment;
import com.bouncy_mehdich.Client.Models.Follow;
import com.bouncy_mehdich.Client.Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class CommentController {

    String postID;
    String userID;
    String commentText;


    public ArrayList<Comment> getPostComments(){
        ArrayList<Comment> postComments = new ArrayList<>();
        String response;

        try{
            URL url = new URL("http://localhost:8080/commments/" + postID);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestMethod("GET");
//                int responseCode = connection.getResponseCode();
//                System.out.println(responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            StringBuffer resp = new StringBuffer();
            while ((input = in.readLine()) != null){
                resp.append(input);
            }
            in.close();
            response = resp.toString();

            JSONArray jsonArray = new JSONArray(response);
            String[] comments = toStringArray(jsonArray);

            for(String comment : comments){
                JSONObject obj = new JSONObject(comment);
                postComments.add(new Comment(obj.getString("postID"), obj.getString("text"), obj.getString("senderID"), null));
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return postComments;
    }

    public void comment(){

        String response;
        Comment comment;
        try {
            URL url = new URL("http://localhost:8080/comments");
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

            comment = new Comment(postID, commentText, userID);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(comment);
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
                // comment sent !!
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

    public static String[] toStringArray(JSONArray array) {
        if(array == null)
            return new String[0];

        String[] arr = new String[array.length()];
        for(int i = 0; i < arr.length; i++)
            arr[i] = array.optString(i);
        return arr;
    }

}
