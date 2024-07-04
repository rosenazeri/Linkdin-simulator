package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.FollowController;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class FollowsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        FollowController followController = new FollowController();

        String response = null;


        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        if (splittedPath[1].equals("following")) {
            if (method.equals("GET")) {
                String userID = splittedPath[2];
                try {
                    response = new Gson().toJson(followController.getFollowings(userID));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
