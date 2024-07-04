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

    public ArrayList<String> getFollowers(String userID) throws SQLException {
        ArrayList<String> followerIDs = new ArrayList<>();
        ArrayList<User> followers = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM follows WHERE followedID = ?");
        statement.setString(1,userID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            followerIDs.add(resultSet.getString("followerID"));
        }

        return followerIDs;

//        UserDAO userDAO = new UserDAO();
//        for(String userID : followerIDs){
//            followers.add(userDAO.getUser(userID));
//        }
//
//        return followers;
    }

    public ArrayList<String> getFollowings(String userID) throws SQLException {
        ArrayList<String> followingIDs = new ArrayList<>();
        ArrayList<User> followings = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM follows WHERE followerID = ?");
        statement.setString(1,userID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            followingIDs.add(resultSet.getString("followedID"));
        }

        return followingIDs;

//        UserDAO userDAO = new UserDAO();
//        for(String ID : followingIDs){
//            followings.add(userDAO.getUser(ID));
//        }
//
//        return followings;
    }

}
