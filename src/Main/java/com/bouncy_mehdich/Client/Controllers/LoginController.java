package Main.java.com.bouncy_mehdich.Client.Controllers;

import Main.java.com.bouncy_mehdich.Client.Models.User;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginController {
    ScreenManager SM;
    String userID = "";
    String pass = "";



    public void login(){

        SM = new ScreenManager();

        if(userID.length() == 0 || pass.length() == 0){
            return; //checking is username or password is null or not
        }
        try{
            URL url = new URL("http://localhost:8080/login/" + userID + "/" + pass); //sending username and password to server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input; // response from server
            StringBuffer resp = new StringBuffer();
            while ((input = in.readLine()) != null) {
                resp.append(input);
            }
            in.close();

            String response = resp.toString();

            if (response.equals("ok")){
                url = new URL("http://localhost:8080/user/" + userID);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                resp = new StringBuffer();
                while ((input = in.readLine()) != null) {
                    resp.append(input);
                }
                in.close();

                response = resp.toString();

                JSONObject obj = new JSONObject(response);
                SM.correntUser = new User(obj.getString("id"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("email"), obj.getString("password"), obj.getString("recovery-string"));


                // toDo: lets go to home page
            } else {
                // toDo: set label false user or pass
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
