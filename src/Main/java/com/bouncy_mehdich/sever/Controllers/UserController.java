package com.bouncy_mehdich.sever.Controllers;

import com.bouncy_mehdich.sever.DB.ProfileDAO;
import com.bouncy_mehdich.sever.DB.UserDAO;
import com.bouncy_mehdich.sever.Models.Profile;
import com.bouncy_mehdich.sever.Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.runtime.ObjectMethods;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class UserController {
    UserDAO userDAO;
    ProfileDAO profileDAO;

    public UserController() {

        userDAO = new UserDAO();
        profileDAO = new ProfileDAO();

    }

    public String addUser(String id, String firstName, String lastName, String email, String password, String recoveryStr) {
        if (doesUserExists(id)){
            return "0";
        }
        User user = new User(id,firstName,lastName,email,password,recoveryStr);
        try {
            userDAO.addUser(user);
            return "1";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(String id, String firstName, String lastName, String email, String password, String recoveryStr) {
        User user = new User(id,firstName,lastName,email,password,recoveryStr);
        try {
            userDAO.updateUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword(String id, String newPassword){
        User user = new User(id,"","","",newPassword,"");
        try {
            userDAO.updatePassword(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByID(String id){
        User user;
        try {
            user = userDAO.getUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user == null){
            return null;
        }

        return user;
    }

    public String getUsers(){
        ArrayList<User> users;
        try {
            users = userDAO.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        try {
            response = objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;

    }

    public void deleteUserByID(String id){
        try {
            userDAO.deleteUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        deleteProfileByID(id);
    }

    public void deleteAllUsers(){
        try {
            userDAO.deleteAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        deleteAllProfiles();
    }

    public void addProfile(String id, String nickName, String bio, String country, String city, String herfe, Date birth, String avatarPath, String backgroundPath){
        if (doesProfileExists(id)){
            return;
        }

        Profile profile = new Profile(id, nickName, bio, country, city, herfe, birth);
        try {
            profileDAO.addProfile(profile, avatarPath, backgroundPath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProfile(String id, String nickName, String bio, String country, String city, String herfe, Date birth, String avatarPath, String backgroundPath){
        Profile profile = new Profile(id, nickName, bio, country, city, herfe, birth);
        try {
            profileDAO.updateProfile(profile, avatarPath, backgroundPath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProfileByID(String id){
        Profile profile;
        try {
            profile = profileDAO.getProfile(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (profile == null){
            return "no user found";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        try {
            response = objectMapper.writeValueAsString(profile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public String getProfiles(){
        ArrayList<Profile> profiles;
        try {
            profiles = profileDAO.getProfiles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        try {
            response = objectMapper.writeValueAsString(profiles);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;

    }

    public void deleteProfileByID(String id){
        try {
            profileDAO.deleteProfile(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllProfiles(){
        try {
            profileDAO.deleteAllProfiles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean doesUserExists(String id) {
        if (id == null) return false;
        try {
            return (userDAO.getUser(id) != null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doesProfileExists(String id) {
        if (id == null) return false;
        try {
            return (profileDAO.getProfile(id) != null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
