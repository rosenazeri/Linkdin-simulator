package Main.java.com.bouncy_mehdich.sever.Controllers;

import Main.java.com.bouncy_mehdich.sever.DB.PostDAO;
import Main.java.com.bouncy_mehdich.sever.Models.Post;

import java.sql.SQLException;

public class PostController {
    PostDAO postDAO;

    public PostController() {
        postDAO = new PostDAO();
    }

    public void addPost(Post post, String mediaPath) throws SQLException {
        postDAO.post(post, mediaPath);
    }
}
