package com.bouncy_mehdich.sever.Controllers;

import com.bouncy_mehdich.sever.DB.LikesDAO;
import com.bouncy_mehdich.sever.Models.Like;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class LikeController {
    LikesDAO likesDAO;

    public LikeController() {
        likesDAO = new LikesDAO();
    }

    public String getAllLikes() throws SQLException {
        ArrayList<Like> likes = likesDAO.getAllLikes();
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        try {
            response = objectMapper.writeValueAsString(likes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public void addLike(String postID, String userID) throws SQLException {
        likesDAO.like(postID, userID);
    }

    public void deleteLike(String postID, String userID) throws SQLException {
        likesDAO.unLike(postID, userID);
    }
}
