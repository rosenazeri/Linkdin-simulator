package com.bouncy_mehdich.sever.Controllers;

import com.bouncy_mehdich.sever.DB.ProfileDAO;
import com.bouncy_mehdich.sever.DB.UserDAO;

public class UserController {
    public UserController() {
        UserDAO userDAO = new UserDAO();
        ProfileDAO profileDAO = new ProfileDAO();
    }
}
