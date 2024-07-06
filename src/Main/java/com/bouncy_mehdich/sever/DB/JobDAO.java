package Main.java.com.bouncy_mehdich.sever.DB;

import Main.java.com.bouncy_mehdich.sever.Models.Job;

import java.sql.*;
import java.util.ArrayList;

public class JobDAO {
    private final String pathOfDB = "jdbc:sqlite:/Users/mehdich/Desktop/Final/Lind-A/test.db";
    private Connection connection;

    public JobDAO() {
        try {
            //connection = DriverManager.getConnection(pathOfDB);
            connection = ConnectDB.Connect();

            PreparedStatement statement = connection.prepareStatement("CREATE TABLE  jobs (workerID VARCHAR, jobTitle VARCHAR, employmentType VARCHAR, company VARCHAR, location VARCHAR, workplaceType VARCHAR, isWorking VARCHAR, startDate DATE, endDate DATE,skills VARCHAR, description VARCHAR, doNotify VARCHAR)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public boolean toBoolean(String str){
        return str.equals("true");
    }
    public String toStr(boolean bool){
        if(bool){
            return "true";
        }else{
            return "false";
        }
    }

    public void addJob(Job job) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO jobs (workerID, jobTitle, employmentType, company, location, workplaceType, isWorking, startDate, endDate, skills, description, doNotify)");
        statement.setString(1,job.getWorkerID());
        statement.setString(2,job.getJobTitle());
        statement.setString(3,job.getEmploymentType());
        statement.setString(4,job.getCompanyName());
        statement.setString(5,job.getWorkLocationType());
        statement.setString(6,job.getWorkLocationType());
        statement.setString(7,toStr(job.isWorking()));
        statement.setDate(8,new java.sql.Date(job.getStartDate().getTime()));
        statement.setDate(9,new java.sql.Date(job.getEndDate().getTime()));
        statement.setString(10,job.getSkills());
        statement.setString(11,job.getDescription());
        statement.setString(12,toStr(job.isDoNotify()));

        statement.executeUpdate();
    }

    public void removeJob(String workerID, String jobTitle) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM jobs WHERE workerID = ? AND jobTitle = ?");
        statement.setString(1,workerID);
        statement.setString(2,jobTitle);

        statement.executeUpdate();
    }

    public ArrayList<Job> userJobs(String workerID) throws SQLException {
        ArrayList<Job> userJobs = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM jobs WHERE workerID = ?");
        statement.setString(1,workerID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            userJobs.add(new Job(resultSet.getString("workerID"), resultSet.getString("jobTitle"), resultSet.getString("employmentType"), resultSet.getString("company"), resultSet.getString("location"), resultSet.getString("workplaceType"), toBoolean(resultSet.getString("isWorking")), resultSet.getDate("startDate"), resultSet.getDate("endDate"), resultSet.getString("skills"), resultSet.getString("description"), toBoolean(resultSet.getString("doNotify"))));
        }

        return userJobs;

    }


}
