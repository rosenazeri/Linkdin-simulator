package Main.java.com.bouncy_mehdich.sever.DB;

import Main.java.com.bouncy_mehdich.sever.Models.Comment;

import java.sql.*;
import java.util.ArrayList;

public class CommentDAO {
    //private final String url = "jdbc:sqlite:/D:/java projects/Lind-A/test.db";
    private final String pathOfDB = "jdbc:sqlite:/Users/mehdich/Desktop/Final/Lind-A/test.db";

    private Connection connection;

    public CommentDAO() {
        try {
            //connection = DriverManager.getConnection(pathOfDB);
            connection = ConnectDB.Connect();

            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS comments (postID VARCHAR, text VARCHAR, senderID VARCHAR, commentDate Date, mediaPath VARCHAR)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void addComment(Comment comment, String mediaPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO likes(postID, text, senderID, commentDate, mediaPath) VALUES (?, ? , ? , ? , ?)");
        statement.setString(1,comment.getPostID());
        statement.setString(2,comment.getCommentText());
        statement.setString(3,comment.getSenderID());
        statement.setDate(4,new java.sql.Date(comment.getDate().getTime()));
        statement.setString(5,null);


        statement.executeUpdate();
    }

    public void delete(String postID, String senderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE postID = ? AND senderID = ?");
        statement.setString(1,postID);
        statement.setString(2,senderID);

        statement.executeUpdate();
    }

    public ArrayList<Comment> getCommentsOfPost(String postID) throws SQLException {

        ArrayList<Comment> postComments = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE postID = ?");
        statement.setString(1,postID);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            postComments.add(new Comment(resultSet.getString("postID"), resultSet.getString("text"), resultSet.getString("senderID"), resultSet.getDate("commentDate")));
        }

        return postComments;
    }
}
