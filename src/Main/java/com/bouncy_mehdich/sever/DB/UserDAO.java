package com.bouncy_mehdich.sever.DB;

import com.bouncy_mehdich.sever.Models.User;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    private final String url = "jdbc:sqlite:/D:/java projects/Lind-A/test.db";
    private Connection connection;
    public UserDAO() {
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id VARCHAR(36) primary key, password VARCHAR(16), recoveryStr VARCHAR(36))");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users(id, password, recoveryStr)");
        statement.setString(1,user.getID());
        statement.setString(2,user.getPassword());
        statement.setString(3,user.getRecoveryStr());

        statement.executeUpdate();
    }

    public void updateUser(User user) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
        statement.setString(1,user.getPassword());
        statement.setString(2, user.getID());

        statement.executeUpdate();
    }

    public User getUser(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setString(1,id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return new User(resultSet.getString("id"),resultSet.getString("password"),resultSet.getString("recoveryStr"));
        }

        return null; //no user found
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            users.add(new User(resultSet.getString("id"),resultSet.getString("password"),resultSet.getString("recoveryStr")));
        }

        return users;
    }

    public void deleteUser(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        statement.setString(1,id);
        statement.executeUpdate();
    }

    public void deleteAllUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users");
        statement.executeUpdate();
    }
}
