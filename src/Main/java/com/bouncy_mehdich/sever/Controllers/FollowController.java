package com.bouncy_mehdich.sever.Controllers;

import com.bouncy_mehdich.sever.DB.FollowDAO;

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
}
