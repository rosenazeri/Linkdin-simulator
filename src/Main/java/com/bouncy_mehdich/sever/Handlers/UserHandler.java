package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.UserController;
import com.bouncy_mehdich.sever.DB.UserDAO;
import com.bouncy_mehdich.sever.Models.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;

public class UserHandler implements HttpHandler {
    public UserHandler() {
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        UserController userController = new UserController();
        String response = null;


        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        // login

        if (splittedPath[1].equals("login")) {
            String userIdInput = splittedPath[2];
            String passwordInput = splittedPath[3];

            User user = userController.getUser(userIdInput);

            if (user != null && user.getPassword().equals(passwordInput)) {
                response = "ok";
            }
        }
        // signUp

        if (method.equals("POST")) {
            // Read the request body
            InputStream requestBody = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            requestBody.close();

            String newUser = body.toString();
            JSONObject jsonObject = new JSONObject(newUser);

            userController.addUser(jsonObject.getString("id"), jsonObject.getString("firstname"), jsonObject.getString("lastname"), jsonObject.getString("email"), jsonObject.getString("password"), jsonObject.getString("recovery-string"), jsonObject.getString("createTime"))

        }
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
