package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.UserController;
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

    }
}
