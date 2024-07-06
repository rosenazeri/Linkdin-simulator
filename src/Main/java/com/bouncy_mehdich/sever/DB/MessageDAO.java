package Main.java.com.bouncy_mehdich.sever.DB;

import Main.java.com.bouncy_mehdich.sever.Models.Message;

import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {

    private final String url = "jdbc:sqlite:/D:/java projects/Lind-A/test.db";
    private Connection connection;
    public MessageDAO() {
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS messages (message VARCHAR, mediaPath VARCHAR, senderID VARCHAR(3000), receiverID VARCHAR, messageDate Date)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void message(Message message, String mediaPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO messages (message, mediaPath, senderID, receiverID, messageDate)");
        statement.setString(1,message.getMessageText());
        statement.setString(2,mediaPath);
        statement.setString(3,message.getSenderID());
        statement.setString(4,message.getReceiverID());
        statement.setDate(5,new java.sql.Date(message.getMessageDate().getTime()));

        statement.executeUpdate();
    }

    public void editMessage(Message message, String oldMessage, String mediaPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE messages set message = ? WHERE message = ? AND mediaPath = ? AND senderID = ? AND receiverID = ? AND messageDate = ?");
        statement.setString(1,message.getMessageText());
        statement.setString(2,oldMessage);
        statement.setString(3,mediaPath);
        statement.setString(4,message.getSenderID());
        statement.setString(5,message.getReceiverID());
        statement.setDate(6,new java.sql.Date(message.getMessageDate().getTime()));

        statement.executeUpdate();
    }

    public void deleteMessage(Message message, String mediaPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM messages WHERE message = ? AND mediaPath = ? AND senderID = ? AND receiverID = ? AND messageDate = ?");
        statement.setString(1,message.getMessageText());
        statement.setString(2,mediaPath);
        statement.setString(3,message.getSenderID());
        statement.setString(4,message.getReceiverID());
        statement.setDate(5,new java.sql.Date(message.getMessageDate().getTime()));

        statement.executeUpdate();
    }

    public ArrayList<Message> getChatMessages(String senderID, String receiverID) throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE senderID = ? AND recieverID = ?");
        statement.setString(1,senderID);
        statement.setString(2,receiverID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            messages.add(new Message(resultSet.getString("message"), resultSet.getString("senderID"), resultSet.getString("receiverID"), resultSet.getDate("messageDate")));
        }
        statement = connection.prepareStatement("SELECT * FROM messages WHERE senderID = ? AND recieverID = ?");
        statement.setString(1,receiverID);
        statement.setString(2,senderID);
        resultSet = statement.executeQuery();

        while (resultSet.next()){
            messages.add(new Message(resultSet.getString("message"), resultSet.getString("senderID"), resultSet.getString("receiverID"), resultSet.getDate("messageDate")));
        }

        return messages;
    }

    public void deleteChat(String senderID, String receiverID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM messages WHERE senderID = ? AND recieverID = ?");
        statement.setString(1,senderID);
        statement.setString(2,receiverID);
        statement.executeUpdate();

        statement = connection.prepareStatement("SELECT * FROM messages WHERE senderID = ? AND recieverID = ?");
        statement.setString(1,receiverID);
        statement.setString(2,senderID);
        statement.executeUpdate();
    }
}
