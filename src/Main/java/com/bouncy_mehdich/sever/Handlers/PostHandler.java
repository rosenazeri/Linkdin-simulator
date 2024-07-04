package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.PostController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PostHandler implements HttpHandler {
    public PostHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PostController postController = new PostController();
        String response = null;


        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");


    }
}
