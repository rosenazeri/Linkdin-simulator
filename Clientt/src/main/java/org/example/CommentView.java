package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;


public class CommentView {

    @FXML
     Button back;

    @FXML
     ImageView Profile;

    @FXML
     Label UserName;

    @FXML
     Label Comment;

    @FXML
     VBox vboxContainer;

    ArrayList<Comment> comments = new ArrayList<>();

    @FXML
    public void handleBackButtonAction(ActionEvent actionEvent) {
        HelloApplication m = new HelloApplication();
        try {
            m.changeScene(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        comments = getPostComments();
        Comment comment;
        for (int i = 0; i < comments.size(); i++) {
            comment = comments.get(i);
            addComment(null, comment.getSenderID(), comment.getCommentText());
        }

    }
    public ArrayList<Comment> getPostComments(){
        ArrayList<Comment> postComments = new ArrayList<>();
        String response;

        try{
            URL url = new URL("http://localhost:8080/commments/" + PostViewerController.currentPostID);
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

    public static String[] toStringArray(JSONArray array) {
        if(array == null)
            return new String[0];

        String[] arr = new String[array.length()];
        for(int i = 0; i < arr.length; i++)
            arr[i] = array.optString(i);
        return arr;
    }



    @FXML
    public void addComment(String profileImagePath, String userName, String commentText) {
        // Create new HBox for the comment
        HBox hbox = new HBox();
        hbox.setPrefSize(1010.0, 57.0);
        hbox.setStyle("-fx-background-color: #ffffff;");
        hbox.setPadding(new Insets(5.0, 10.0, 5.0, 10.0));

        // Create ImageView for the profile image
        ImageView profileImage = new ImageView(new Image(profileImagePath));
        profileImage.setFitHeight(47.0);
        profileImage.setFitWidth(49.0);
        profileImage.setPickOnBounds(true);
        profileImage.setPreserveRatio(true);
        HBox.setMargin(profileImage, new Insets(5.0, 5.0, 5.0, 10.0));

        // Create Label for the username
        Label userNameLabel = new Label(userName);
        userNameLabel.setPrefSize(129.0, 15.0);
        userNameLabel.setTextFill(Color.BLUE);
        userNameLabel.setFont(new Font("Lucida Bright", 12));
        HBox.setMargin(userNameLabel, new Insets(20.0, 0.0, 0.0, 10.0));

        // Create Label for the comment
        Label commentLabel = new Label(commentText);
        commentLabel.setPrefSize(752.0, 15.0);
        commentLabel.setTextFill(Color.BLUE);
        commentLabel.setFont(new Font("Lucida Bright", 12.0));
        HBox.setMargin(commentLabel, new Insets(20.0, 0.0, 0.0, 0.0));

        // Add children to HBox
        hbox.getChildren().addAll(profileImage, userNameLabel, commentLabel);

        // Add HBox to the top of VBox
        vboxContainer.getChildren().add(0, hbox);
    }
}