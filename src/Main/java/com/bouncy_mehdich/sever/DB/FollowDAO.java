package com.bouncy_mehdich.sever.DB;

import com.bouncy_mehdich.sever.Models.User;

import java.sql.*;
import java.util.ArrayList;

public class FollowDAO {

    private final String url = "jdbc:sqlite:/D:/java projects/Lind-A/test.db";
    private Connection connection;

    public FollowDAO() {
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS follows (followedID VARCHAR, followerID VARCHAR)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void follow(String followedID, String followerID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO follows (followedID, followerID)");
        statement.setString(1,followedID);
        statement.setString(2,followerID);
        statement.executeUpdate();
    }

    public void unfollow(String followedID, String followerID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM follows WHERE followedID = ? AND followerID = ?");
        statement.setString(1,followedID);
        statement.setString(2,followerID);
        statement.executeUpdate();
    }

    public ArrayList<User> getFollowers(String followedID) throws SQLException {
        ArrayList<String> userIDs = new ArrayList<>();
        ArrayList<User> followers = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM follows WHERE followedID = ?");
        statement.setString(1,followedID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            userIDs.add(resultSet.getString("followerID"));
        }

        UserDAO userDAO = new UserDAO();
        for(String userID : userIDs){
            followers.add(userDAO.getUser(userID));
        }

        return followers;
    }

}
