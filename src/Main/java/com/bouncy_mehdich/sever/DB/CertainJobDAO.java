package Main.java.com.bouncy_mehdich.sever.DB;

import Main.java.com.bouncy_mehdich.sever.Models.CertainJobs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CertainJobDAO {

    private final String pathOfDB = "jdbc:sqlite:/Users/mehdich/Desktop/Final/Lind-A/test.db";
    private Connection connection;
    public CertainJobDAO() {
        try {
            //connection = DriverManager.getConnection(pathOfDB);
            connection = ConnectDB.Connect();
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS certainJobs (userID VARCHAR primary key, title VARCHAR , employment VARCHAR, company VARCHAR, workPlace VARCHAR, workPlaceType VARCHAR, startWorking Date, finishedWorking Date, explanation VARCHAR, skills VARCHAR)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void addCertainJob(String userID, CertainJobs certainJobs) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO certainJobs (userID,title,emplyment,company,workPlace,workPlaceType,startWorking,finishedWorking,explanation,skills) VALUES (?,?,?,?,?,?,?,?,?,?)");
        statement.setString(1,userID);
        statement.setString(2,certainJobs.getTitle());
        statement.setString(3,certainJobs.getEmployment());
        statement.setString(4,certainJobs.getCompany());
        statement.setString(5,certainJobs.getWorkPlace());
        statement.setString(6,certainJobs.getWorkPlaceType());
        statement.setDate(7,new java.sql.Date(certainJobs.getStartWorking().getTime()));
        statement.setDate(8,new java.sql.Date(certainJobs.getFinishedWorking().getTime()));
        statement.setString(9,certainJobs.getExplanation());
        statement.setString(10,certainJobs.getSkills());

        statement.executeUpdate();

    }
}
