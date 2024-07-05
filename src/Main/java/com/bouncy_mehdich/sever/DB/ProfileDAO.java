package com.bouncy_mehdich.sever.DB;

import com.bouncy_mehdich.sever.Models.Profile;
import com.bouncy_mehdich.sever.Models.User;

import java.sql.*;
import java.util.ArrayList;

public class ProfileDAO {
    private final String pathOfDB = "jdbc:sqlite:/Users/mehdich/Desktop/Final/Lind-A/test.db";
    private Connection connection;
    public ProfileDAO() {
        try {
            connection = DriverManager.getConnection(pathOfDB);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS profiles(id VARCHAR(36) primary key, nickName VARCHAR(40), bio VARCHAR(200), avatar_path VARCHAR, background_path VARCHAR, country VARCHAR(60), city VARCHAR(60), herfe VARCHAR(60), birth DATE)");
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("db error: " + e.getMessage());
        }
    }

    public void addProfile(Profile profile,String AvatarPath, String BackgroundPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO profiles(id, nickName, bio, avatar_path, background_path, country, city, herfe, birth)");
        statement.setString(1,profile.getID());
        statement.setString(2,profile.getNickName());
        statement.setString(3,profile.getBiography());
        statement.setString(4,AvatarPath);
        statement.setString(5,BackgroundPath);
        statement.setString(6,profile.getCountry());
        statement.setString(7,profile.getCity());
        statement.setString(8,profile.getHerfe());
        statement.setDate(9,new java.sql.Date(profile.getBirth().getTime()));

        statement.executeUpdate();
    }

    public void updateProfile(Profile profile,String AvatarPath, String BackgroundPath) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE profiles SET nickName = ?, bio = ?, avatar_path = ?, background_path = ?, country = ?, city = ?, herfe = ?, birth = ? WHERE id = ?");
        statement.setString(1,profile.getNickName());
        statement.setString(2,profile.getBiography());
        statement.setString(3,AvatarPath);
        statement.setString(4,BackgroundPath);
        statement.setString(5,profile.getCountry());
        statement.setString(6,profile.getCity());
        statement.setString(7,profile.getHerfe());
        statement.setDate(8,new java.sql.Date(profile.getBirth().getTime()));
        statement.setString(9,profile.getID());

        statement.executeUpdate();
    }

    public Profile getProfile(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM profiles WHERE id = ?");
        statement.setString(1,id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return new Profile(resultSet.getString("id"),resultSet.getString("nickName"),resultSet.getString("bio"),resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("herfe"),resultSet.getDate("birth"));
        }

        return null; //no user found
    }

    public ArrayList<Profile> getProfiles() throws SQLException {
        ArrayList<Profile> profiles = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM profiles");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            profiles.add(new Profile(resultSet.getString("id"),resultSet.getString("nickName"),resultSet.getString("bio"),resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("herfe"),resultSet.getDate("birth")));
        }

        return profiles;
    }

    public void deleteProfile(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM profiles WHERE id = ?");
        statement.setString(1,id);
        statement.executeUpdate();
    }

    public void deleteAllProfiles() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM profiles");
        statement.executeUpdate();
    }


}
