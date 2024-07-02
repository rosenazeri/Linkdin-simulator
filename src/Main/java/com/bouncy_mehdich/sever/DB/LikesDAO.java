package com.bouncy_mehdich.sever.DB;

import com.bouncy_mehdich.sever.Models.Like;
import com.bouncy_mehdich.sever.Models.User;

import java.sql.*;
import java.util.ArrayList;

public class LikesDAO {

    private final String url = "jdbc:sqlite:/D:/java projects/Lind-A/test.db";
    private Connection connection;

    public LikesDAO() {
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS likes (postID VARCHAR, likerID VARCHAR, likerFirstName VARCHAR(20), likerLastName VARCHAR(40))");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void like(Like like) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO likes(postID, likerID, likerFirstName, likerLastName)");
        statement.setString(1,like.getPostID());
        statement.setString(2,like.getLikerID());
        statement.setString(3,like.getLikerFirstName());
        statement.setString(4,like.getLikerLastName());

        statement.executeUpdate();
    }

    public void unLike(Like like) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM likes WHERE postID = ? AND likerID = ?");
        statement.setString(1,like.getPostID());
        statement.setString(2,like.getLikerID());

        statement.executeUpdate();
    }

    public ArrayList<Like> getPostLikes(String postID) throws SQLException {
        ArrayList<Like> postLikes = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM likes WHERE postID = ?");
        statement.setString(1,postID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            postLikes.add(new Like(resultSet.getString("postID"),resultSet.getString("likerID"),resultSet.getString("likerFirstName"),resultSet.getString("likerLastName")));
        }

        return postLikes;
    }

    public void deleteAllLikes() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM likes");
        statement.executeUpdate();
    }
}
