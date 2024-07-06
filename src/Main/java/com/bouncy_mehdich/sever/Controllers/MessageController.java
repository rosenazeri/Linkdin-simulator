package Main.java.com.bouncy_mehdich.sever.Controllers;


import Main.java.com.bouncy_mehdich.sever.DB.MessageDAO;
import Main.java.com.bouncy_mehdich.sever.DB.UserDAO;
import Main.java.com.bouncy_mehdich.sever.Models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class MessageController {
    private static MessageDAO messageDAO = null;
    private  static UserDAO userDAO = null;

    public MessageController() throws SQLException {
        messageDAO = new MessageDAO();
        userDAO = new UserDAO();
    }

    public void addMessage(String sender, String receiver, String text) throws SQLException {
        if (userDAO.getUser(sender) == null || userDAO.getUser(receiver) == null)
            return;

        Message message = new Message(sender, receiver, text);
        messageDAO.message(message, null);
        return;
    }
    public void deleteMessage(String id) throws SQLException {
        if (messageDAO.getMessage(id) != null)
            return;
        messageDAO.deleteMessage(id);
        return;
    }
    public String getMessages(String user1, String user2) throws SQLException, JsonProcessingException {
        if (userDAO.getUser(user1) == null || userDAO.getUser(user2) == null)
            return "problem getMessages";

        ArrayList<Message> messages = messageDAO.getMessages(user1, user2);
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(messages);
        return response;
    }
    public String getMessagesReceived(String receiver, int cnt) throws SQLException, JsonProcessingException {
        if (userDAO.getUser(receiver) == null)
            return "";

        ArrayList<Message> messages = messageDAO.getMessagesReceived(receiver);
        cnt = Integer.min(cnt, messages.size());
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(messages.subList(messages.size() - cnt, messages.size()));
        return response;
    }
}
