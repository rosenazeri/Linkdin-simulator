package Main.java.com.bouncy_mehdich.sever.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDB {
    private final static String pathOfDB = "jdbc:sqlite:/Users/mehdich/Desktop/Final/Lind-A/test.db";
    private static Connection connection;

    public static Connection Connect() {
        if (connection != null) {
            return connection;
        }
        else {
            try {
                connection = DriverManager.getConnection(pathOfDB);
            } catch (SQLException e) {
                System.out.println("db error: " + e.getMessage());
            }
            finally {
                return connection;
            }
        }


    }
}
