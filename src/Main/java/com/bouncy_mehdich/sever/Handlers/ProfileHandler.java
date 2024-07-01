package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.ProfileController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ProfileHandler implements HttpHandler {
    public ProfileHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        ProfileController profileController = new ProfileController();

    }
}
