package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.LikeController;
import com.bouncy_mehdich.sever.Controllers.UserController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class LikesHandler implements HttpHandler {
    public LikesHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        LikeController likeController = new LikeController();
        String response = null;


        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        if (method.equals("GET")) {
            if (splittedPath.length == 2) {
                try {
                    response = likeController.getAllLikes();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if (method.equals("POST")) {
            String postID = splittedPath[2];
            String userID = splittedPath[3];

            try {
                likeController.addLike(postID, userID);
                response = "done";
            } catch (SQLException e) {
                response = "like not add";
                throw new RuntimeException(e);
            }
        } else if (method.equals("DELETE")) {
            String postID = splittedPath[2];
            String userID = splittedPath[3];

            try {
                likeController.deleteLike(postID, userID);
                response = "done";
            } catch (SQLException e) {
                response = "delete like not done";
                throw new RuntimeException(e);
            }
        }


        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());

        os.close();


    }
}

