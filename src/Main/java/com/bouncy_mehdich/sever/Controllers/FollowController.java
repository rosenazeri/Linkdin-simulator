package com.bouncy_mehdich.sever.Controllers;

import com.bouncy_mehdich.sever.DB.FollowDAO;
import com.bouncy_mehdich.sever.Models.Follow;
import com.bouncy_mehdich.sever.Models.Like;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class FollowController {
    FollowDAO followDAO;

    public FollowController() {
        followDAO = new FollowDAO();
    }

    public ArrayList<String> getFollowings(String userID) throws SQLException {
        return followDAO.getFollowings(userID);
    }

    public String getAllFollows() throws SQLException {
        ArrayList<Follow> follows = followDAO.getFollows();
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        try {
            response = objectMapper.writeValueAsString(follows);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public void addFollow(String followerID, String followedID) throws SQLException {

        followDAO.follow(followedID, followerID);

    }

    public void deleteFollow(String followerID, String followedID) throws SQLException {

        followDAO.unfollow(followedID, followerID);

    }
}
