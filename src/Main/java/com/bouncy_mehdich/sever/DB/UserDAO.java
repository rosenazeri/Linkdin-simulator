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
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id VARCHAR(36) primary key, firstName VARCHAR(20), lastName VARCHAR(40), email VARCHAR, password VARCHAR(16), recoveryStr VARCHAR(36)), createTime Date");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users(id, firstName, lastName, email, password, recoveryStr, createTime)");
        statement.setString(1,user.getID());
        statement.setString(2,user.getFirstName());
        statement.setString(3,user.getLastName());
        statement.setString(4,user.getEmail());
        statement.setString(5,user.getPassword());
        statement.setString(6,user.getRecoveryStr());
        statement.setDate(7, new java.sql.Date(user.getCreateTime().getTime()));

        statement.executeUpdate();
    }

    public void updatePassword(User user) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
        statement.setString(1,user.getPassword());
        statement.setString(2, user.getID());

        statement.executeUpdate();
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, email = ? WHERE id = ?");
        statement.setString(1,user.getFirstName());
        statement.setString(2,user.getLastName());
        statement.setString(3,user.getEmail());
        statement.setString(4,user.getID());
    }

    public User getUser(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setString(1,id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return new User(resultSet.getString("id"),resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getString("recoveryStr"));
        }

        return null; //no user found
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            users.add(new User(resultSet.getString("id"),resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getString("recoveryStr")));
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
