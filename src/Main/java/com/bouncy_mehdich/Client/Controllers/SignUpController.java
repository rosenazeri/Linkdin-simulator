package com.bouncy_mehdich.Client.Controllers;

import com.bouncy_mehdich.sever.Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUpController {

    private String userEmail;
    private String userID;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userRecoveryStr;


    public void signUp() {
        try {
            String response;

            //GET request
            {
                URL url = new URL("http://localhost:8080/user");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input;
                StringBuffer resp = new StringBuffer();
                while ((input = in.readLine()) != null){
                    resp.append(input);
                }
                in.close();
                response = resp.toString();

                JSONArray jsonObj = new JSONArray(response);
                String[] users = toStringArray(jsonObj);

                for (String str: users){
                    JSONObject obj = new JSONObject(str);
                    User user = new User(obj.getString("id"),obj.getString("firstName"),obj.getString("lastName"), obj.getString("email"), obj.getString("password"), obj.getString("recovery-string"));
                    if (user.getEmail().equals(userEmail)){
                        return; //email in use
                    }
                    if (user.getID().equals(userID)){
                        return; //username in use
                    }
                }
            }

            //POST request
            {
                URL url = new URL("http://localhost:8080/user");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                User user = new User(userID,userFirstName,userLastName,userEmail,userPassword,userRecoveryStr);

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(user);
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
                    // signed up !!
                } else {
                    // server error !!
                }


            }





        } catch (MalformedURLException e) {
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
