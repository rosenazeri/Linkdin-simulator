package com.bouncy_mehdich.sever.DB;

import com.bouncy_mehdich.sever.Models.Post;

import java.sql.*;
import java.util.ArrayList;

public class PostDAO {

    private final String url = "jdbc:sqlite:/D:/java projects/Lind-A/test.db";
    private Connection connection;
    public PostDAO() {
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS posts (postID VARCHAR, postCaption VARCHAR(3000), senderID VARCHAR, mediaPath VARCHAR, postDate Date)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void post(Post post, String mediaPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO posts(postID, postCaption, senderID, mediaPath, postDate)");
        statement.setString(1,post.getPostID());
        statement.setString(2,post.getCaption());
        statement.setString(3,post.getSenderID());
        statement.setString(4,mediaPath);
        statement.setDate(5,new java.sql.Date(post.getPostDate().getTime()));

        statement.executeUpdate();
    }

    public void deletePost(String postID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM posts WHERE postID = ?");
        statement.setString(1,postID);
        statement.executeUpdate();
    }

    public Post getPost(String postID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE postID = ?");
        statement.setString(1,postID);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return new Post(resultSet.getString("postID"), resultSet.getString("postCaption"), resultSet.getString("senderID"), resultSet.getDate("postDate"));
        }

        return null; //no post found
    }

    public ArrayList<Post> getUserPosts(String senderID) throws SQLException {
        ArrayList<Post> userPosts = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE senderID = ?");
        statement.setString(1,senderID);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            userPosts.add(new Post(resultSet.getString("postID"), resultSet.getString("postCaption"), resultSet.getString("senderID"), resultSet.getDate("postDate")));
        }

        return userPosts;
    }

}
