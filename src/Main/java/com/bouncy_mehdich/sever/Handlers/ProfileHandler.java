package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.ProfileController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ProfileHandler implements HttpHandler {
    public ProfileHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        ProfileController profileController = new ProfileController();

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String response = "";
        String[] splitPath = path.split("/");

        switch (method) {
            case "GET":
                if (splitPath.length == 2) {

                }
                break;
            case "POST":
                break;
            case "PUT":
                break;
            case "DELETE":
                break;
            default:
                break;
        }

        exchange.sendResponseHeaders(200,response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
