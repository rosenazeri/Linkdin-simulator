package com.bouncy_mehdich.Client.Controllers;

import com.bouncy_mehdich.Client.Models.Post;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class postViewController {

    String userID;

    public ArrayList<Post> getPosts(){

        ArrayList<Post> showingPosts = new ArrayList<>();
        String response;

        try {

            URL url = new URL("http://localhost:8080/post");
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
            String[] posts = toStringArray(jsonObj);

            ArrayList<String> followings = getFollowings(userID);


            for (String str : posts){
                JSONObject obj = new JSONObject(str);
                for(String following : followings){
                    if(obj.getString("senderID").equals(following)){
                        showingPosts.add(new Post(obj.getString("postID"), obj.getString("postCaption"), obj.getString("senderID"), new Date() /* TODO : get postDate from server */ ));
                    }
                }
            }

            // TODO : show all posts in a scroll pane


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return showingPosts;

    }

    public ArrayList<String> getFollowings(String id){
        ArrayList<String> followingIDs = new ArrayList<>();
        String response;

        try{
            URL url = new URL("http://localhost:8080/following/" + id);
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
            String[] followings = toStringArray(jsonObj);

            for(String str : followings){
                JSONObject obj = new JSONObject(str);
                followingIDs.add(obj.getString("id"));
            }



        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return followingIDs;


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
