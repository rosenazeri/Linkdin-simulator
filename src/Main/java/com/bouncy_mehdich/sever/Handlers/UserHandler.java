package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.UserController;
import com.bouncy_mehdich.sever.DB.UserDAO;
import com.bouncy_mehdich.sever.Models.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class UserHandler implements HttpHandler {
    public UserHandler() {
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        UserController userController = new UserController();

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        // login

        if (splittedPath[1].equals("login")) {

            loginHandle(splittedPath[2], splittedPath[3]);

        }

    }

    public void loginHandle(String userIdInput, String passwordInput) {
        User user = UserDAO.getUser(userIdInput);
    }
}
