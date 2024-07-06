package Main.java.com.bouncy_mehdich.sever.Controllers;

import Main.java.com.bouncy_mehdich.sever.DB.CommentDAO;
import Main.java.com.bouncy_mehdich.sever.Models.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommentController {
    CommentDAO commentDAO;

    public CommentController() {
        commentDAO = new CommentDAO();
    }

    public String getCommentsOfPost(String postID) throws SQLException {
        ArrayList<Comment> comments = commentDAO.getCommentsOfPost(postID);

        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        try {
            response = objectMapper.writeValueAsString(comments);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public void addComment(Comment comment, String mediaPath) throws SQLException {
        commentDAO.addComment(comment, mediaPath);
    }
}
