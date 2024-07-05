package com.bouncy_mehdich.Client.Controllers;

import com.bouncy_mehdich.Client.Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class SignUpController {

    private String userEmail = "kossher";
    private String userID = "kossher";
    private String userFirstName = "kossher";
    private String userLastName = "kossher";
    private String userPassword = "kossher";
    private String userPasswordRepeat = "kossher";
    private String userRecoveryStr = "kossher";


    public void signUp() {

        boolean firstNameEntered = userFirstName.length() != 0;
        boolean lastNameEntered = userLastName.length() != 0;
        boolean IDEntered = userID.length() != 0;
        boolean passEntered = userPassword.length() != 0;
        boolean passRepeatEntered = userPasswordRepeat.length() != 0;
        boolean emailEntered = userEmail.length() != 0;
        boolean recoveryStrEntered = userRecoveryStr.length() != 0;

        if(!(firstNameEntered && lastNameEntered && IDEntered && passEntered && emailEntered && recoveryStrEntered)){
            // TODO : set label text : please enter all fields
        }else if(!isValidEmailAddress(userEmail)){
            // TODO : set label text : please enter a valid email
        } else if(!userPassword.equals(userPasswordRepeat)) {
            // TODO : set label text : pass and passRepeat does not match
        }

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
                while ((input = in.readLine()) != null){
                    resp.append(input);
                }
                in.close();
                response = resp.toString();
                //response = response.substring(1);

                if(!response.equals("[]")) {

                    JSONArray jsonObj = new JSONArray(response);
                    String[] users = toStringArray(jsonObj);

                    for (String str : users) {
                        JSONObject obj = new JSONObject(str);
                        User user = new User(obj.getString("id"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("email"), obj.getString("password"), obj.getString("recovery-string"));
                        if (user.getEmail().equals(userEmail)) {
                            return; // TODO : SET LABEL TEXT : email in use
                        }
                        if (user.getID().equals(userID)) {
                            return; // TODO : set label text : username in use
                        }
                    }

                }

            }

            //POST request
            {
                URL url = new URL("http://localhost:8080/user");
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

                createdUser = new User(userID,userFirstName,userLastName,userEmail,userPassword,userRecoveryStr);

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(createdUser);
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
                    // signed up !!
                } else {
                    // server error !!
                }


            }

            ScreenManager SM =  new ScreenManager();
            SM.correntUser = createdUser;


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
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

    public static boolean isValidEmailAddress(String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        return valid;
    }
}
