package Main.java.com.bouncy_mehdich.sever.Controllers;

import Main.java.com.bouncy_mehdich.sever.DB.PostDAO;
import Main.java.com.bouncy_mehdich.sever.Models.Post;
import Main.java.com.bouncy_mehdich.sever.Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Pos;

import java.sql.SQLException;
import java.util.ArrayList;

public class PostController {
    PostDAO postDAO;

    public PostController() {
        postDAO = new PostDAO();
    }

    public void addPost(Post post, String mediaPath) throws SQLException {
        postDAO.post(post, mediaPath);
    }

    public String getAllPosts() {
        ArrayList<Post> posts;
        try {
            posts = postDAO.getAllPosts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        try {
            response = objectMapper.writeValueAsString(posts);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
