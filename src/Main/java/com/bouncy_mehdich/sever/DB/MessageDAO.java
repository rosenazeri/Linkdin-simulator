package Main.java.com.bouncy_mehdich.sever.DB;

import Main.java.com.bouncy_mehdich.sever.Models.Message;

import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {

    private final String pathOfDB = "jdbc:sqlite:/Users/mehdich/Desktop/Final/Lind-A/test.db";
    private Connection connection;
    public MessageDAO() {
        try {
            //connection = DriverManager.getConnection(pathOfDB);
            connection = ConnectDB.Connect();

            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS messages (id VARCHAR, message VARCHAR, mediaPath VARCHAR, senderID VARCHAR(3000), receiverID VARCHAR, messageDate Date)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void message(Message message, String mediaPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO messages (id, message, mediaPath, senderID, receiverID, messageDate) VALUES (?,?,?,?,?,?)");
        statement.setString(1,message.getId());
        statement.setString(2,message.getMessageText());
        statement.setString(3,mediaPath);
        statement.setString(4,message.getSenderID());
        statement.setString(5,message.getReceiverID());
        statement.setDate(6,new java.sql.Date(message.getMessageDate().getTime()));

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

    public void deleteMessage(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM messages WHERE id = ?");
        statement.setString(1,id);


        statement.executeUpdate();
    }

    public ArrayList<Message> getMessagesReceived(String receiver) throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE receiverID = ?");
        statement.setString(1, receiver);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getString("id"));
            message.setSenderID(resultSet.getString("senderID"));
            message.setReceiverID(resultSet.getString("receiverID"));
            message.setMessageText(resultSet.getString("message"));
            message.setMessageDate(resultSet.getDate("messageDate"));
            messages.add(message);
        }
        return messages;
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
    public Message getMessage(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE id = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Message message = new Message();
            message.setId(id);
            message.setSenderID(resultSet.getString("senderID"));
            message.setReceiverID(resultSet.getString("receiverID"));
            message.setMessageText(resultSet.getString("message"));
            message.setMessageDate(resultSet.getDate("timeStamp"));
            return message;
        }
        return null;
    }
    public ArrayList<Message> getMessages(String user1, String user2) throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE (senderID = ? AND receiverID = ?) or (senderID = ? AND receiverID = ?)");
        statement.setString(1, user1);
        statement.setString(2, user2);
        statement.setString(3, user2);
        statement.setString(4, user1);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getString("id"));
            message.setSenderID(resultSet.getString("sender"));
            message.setReceiverID(resultSet.getString("receiver"));
            message.setMessageText(resultSet.getString("text"));
            message.setMessageDate(resultSet.getDate("timeStamp"));
            messages.add(message);
        }
        return messages;
    }
}
