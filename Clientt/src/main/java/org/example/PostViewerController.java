package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.text.StringContent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class PostViewerController {
    boolean likeState = false;
    @FXML
    AnchorPane anchorPane;

    @FXML
    ScrollPane scrollPane;

    @FXML
    VBox mainVBox;

    @FXML
     ImageView image;

    @FXML
     Label UserName;

    @FXML
     Button follow;

    @FXML
     ImageView ImageMedia;

    @FXML
     Label Caption;

    @FXML
     Label likesNum;

    @FXML
     Button Like;

    @FXML
     Button ShowLikes;

    @FXML
     TextField comment;

    @FXML
     Button showCM;

    @FXML
     Button nextBTN;

    ArrayList<Post> posts = new ArrayList<>();
    int index;
    public static String currentPostID;




    // Example action methods (implement as needed)
    @FXML
    private void likeHandler(ActionEvent actionEvent) {
        Button likeButton = (Button) anchorPane.lookup("#Like"); // Get the Like button by its ID

        if (!likeState) { // If not liked (blue)
            likeButton.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 18.0; -fx-font-family: 'Yu Gothic UI Regular'; -fx-background-color: red;");
            likeState = true;
        } else { // If already liked (red)
            likeButton.setStyle("-fx-text-fill: #1e00ff; -fx-font-size: 18.0; -fx-font-family: 'Yu Gothic UI Regular'; -fx-background-color: #bfb8ff;");
            likeState = false;
        }
    }

    public ArrayList<Post> getPosts(){

        ArrayList<Post> showingPosts = new ArrayList<>();
        String response;

        try {

            URL url = new URL("http://localhost:8080/post");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //int responseCode = connection.getResponseCode();

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

            ArrayList<String> followings = getFollowings(HelloApplication.currentUser.getID());


            for (String str : posts){
                JSONObject obj = new JSONObject(str);
                for(String following : followings){
                    if(obj.getString("senderID").equals(following)){
                        showingPosts.add(new Post(obj.getString("postID"), obj.getString("postCaption"), obj.getString("senderID"), null,obj.getInt("postLikes"),obj.getInt("postComments"), null /* TODO : get mediaPath from server */ ));
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

    @FXML
    public void initialize() {
        posts = getPosts();
        if (posts.isEmpty()) {
            Label test = new Label();
            test.setText("is Empty");
            return;
        }
        Post firstPost = posts.get(0);
        index = 0;
        currentPostID = firstPost.getSenderID();
        UserName.setText(firstPost.getSenderID());
        Caption.setText(firstPost.getCaption());
        likesNum.setText("" + firstPost.getLikes());
    }

    @FXML
    private void followHandler(ActionEvent actionEvent) {
        // Implement logic for follow button click
    }

    @FXML
    private void showCommentHandler(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void nextButtonClicked(ActionEvent actionEvent){
        index++;
        if(posts.size() > index){
            UserName.setText(posts.get(index).getSenderID());
            Caption.setText(posts.get(index).getCaption());
            likesNum.setText("" + posts.get(index).getLikes());
            currentPostID = posts.get(index).getPostID();
        }
        if(index == posts.size()-1){
            nextBTN.setText("no more post");
        }
    }

    public void comment(String postID, String commentText, String userID){

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

    public void ShowLikesHandler(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(18);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SendHandler(ActionEvent actionEvent) {
        if(!comment.getText().isEmpty()){
            comment(posts.get(index).getPostID(),comment.getText(),HelloApplication.currentUser.getID());
        }
    }

}
