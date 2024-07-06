package Main.java.com.bouncy_mehdich.Client.Controllers;

import Main.java.com.bouncy_mehdich.Client.Models.Follow;
import Main.java.com.bouncy_mehdich.Client.Models.Like;
import Main.java.com.bouncy_mehdich.Client.Models.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

public class PostController {

    String userID; // get this from the main class
    String postCaption;

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
                        showingPosts.add(new Post(obj.getString("postID"), obj.getString("postCaption"), obj.getString("senderID"), null, obj.getInt("postLikes"), obj.getInt("postComments"), null /* TODO : get mediaPath from server */ ));
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

    public void post(){

        try{
            String response;
            URL url = new URL("http://localhost:8080/post");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Post post = new Post(postCaption, userID, null);

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

    public void followHandle(String postSenderID){
        String response;

        if(isFollowed(postSenderID)){
            try{
                URL url = new URL("http://localhost:8080/follows/" + userID + "/" + postSenderID);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
                connection.setRequestMethod("DELETE");
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

                if(response.equals("delete follow done")){
                    // TODO : change button text to like
                }else{
                    System.out.println(response);
                }

            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try{
                URL url = new URL("http://localhost:8080/follows/" + userID + "/" + postSenderID);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
                connection.setRequestMethod("Post");
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

                if(response.equals("add follow done")){
                    // TODO : change button text to unLike
                }else{
                    System.out.println(response);
                }

            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void likeHandle(String postID){
        String response;

        if(isLiked(postID)){
            try{
                URL url = new URL("http://localhost:8080/likes/" + postID + "/" + userID);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
                connection.setRequestMethod("DELETE");
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

                if(response.equals("done")){
                    // TODO : change button text to like
                }else{
                    System.out.println(response);
                }

            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try{
                URL url = new URL("http://localhost:8080/likes/" + postID + "/" + userID);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
                connection.setRequestMethod("Post");
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

                if(response.equals("done")){
                    // TODO : change button text to unLike
                }else{
                    System.out.println(response);
                }

            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isLiked(String currentPostID){

        ArrayList<Like> AllLikes = getLikes();

        for (Like like : AllLikes){
            if(like.getLikerID().equals(userID) && like.getPostID().equals(currentPostID)){
                return true;
            }
        }

        return false;
    }

    public boolean isFollowed(String currentPostSenderID){
        ArrayList<Follow> AllFollows = getFollows();

        for (Follow follow : AllFollows){
            if(follow.getFollowerID().equals(userID) && follow.getFollowedID().equals(currentPostSenderID)){
                return true;
            }
        }

        return false;
    }

    public ArrayList<Like> getLikes(){
        ArrayList<Like> AllLikes = new ArrayList<>();
        String response;

        try{
            URL url = new URL("http://localhost:8080/likes");
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
            String[] likes = toStringArray(jsonArray);

            for(String like : likes){
                JSONObject obj = new JSONObject(like);
                AllLikes.add(new Like(obj.getString("postID"), obj.getString("likerID")));
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return AllLikes;
    }

    public ArrayList<Follow> getFollows(){
        ArrayList<Follow> AllFollows = new ArrayList<>();
        String response;

        try{
            URL url = new URL("http://localhost:8080/follows");
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
            String[] follows = toStringArray(jsonArray);

            for(String follow : follows){
                JSONObject obj = new JSONObject(follow);
                AllFollows.add(new Follow(obj.getString("followerID"), obj.getString("followedID")));
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return AllFollows;

    }

    public void handleComment(String postID){
        CommentController commentController = new CommentController();
        commentController.postID = postID;
        commentController.userID = userID;
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
