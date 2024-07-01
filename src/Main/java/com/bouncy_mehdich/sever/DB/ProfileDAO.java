package com.bouncy_mehdich.sever.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileDAO {
    private final String pathOfDB = "jdbc:sqlite:/D:/java projects/Lind-A/test.db";
    public ProfileDAO() {
        try {
            Connection connection = DriverManager.getConnection(pathOfDB);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS profiles()");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }


}
