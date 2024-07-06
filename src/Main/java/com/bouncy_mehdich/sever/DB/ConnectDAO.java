package Main.java.com.bouncy_mehdich.sever.DB;

import Main.java.com.bouncy_mehdich.sever.Models.User;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDAO {

    private final String pathOfDB = "jdbc:sqlite:/Users/mehdich/Desktop/Final/Lind-A/test.db";
    private Connection connection;

    public ConnectDAO() {
        try {
            connection = DriverManager.getConnection(pathOfDB);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS connects (user1 VARCHAR, user2 VARCHAR)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void connect(String user1, String user2) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO connects (user1, user2)");
        statement.setString(1,user1);
        statement.setString(2,user2);
        statement.executeUpdate();
    }

    public void disconnect(String user1, String user2) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM connects WHERE user1 = ? AND user2 = ?");
        statement.setString(1,user1);
        statement.setString(2,user2);
        statement.executeUpdate();
    }

    public ArrayList<User> getConnects(String user1) throws SQLException {
        ArrayList<String> userIDs = new ArrayList<>();
        ArrayList<User> connects = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM connects WHERE user1 = ?");
        statement.setString(1,user1);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            userIDs.add(resultSet.getString("user2"));
        }

        UserDAO userDAO = new UserDAO();
        for(String userID : userIDs){
            connects.add(userDAO.getUser(userID));
        }

        return connects;
    }
}
