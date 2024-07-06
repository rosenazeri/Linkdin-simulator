package Main.java.com.bouncy_mehdich.Client.Controllers;

import Main.java.com.bouncy_mehdich.Client.Models.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class SearchUserController {
    //String searchPhrase;

    public ArrayList<User> searchUser(String searchPhrase){

        ArrayList<User> resultUsers = new ArrayList<>();
        String response;
        try {
            URL url = new URL("http://localhost:8080/search/" + searchPhrase);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestMethod("GET");

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
                    resultUsers.add(user);
                }

            }





        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultUsers;
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
