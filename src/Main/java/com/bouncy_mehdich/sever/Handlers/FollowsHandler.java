package Main.java.com.bouncy_mehdich.sever.Handlers;

import Main.java.com.bouncy_mehdich.sever.Controllers.FollowController;
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

        if (splittedPath[1].equals("follows")) {
            if (method.equals("GET")) {
                try {
                    response = followController.getAllFollows();
                } catch (SQLException e) {
                    response = "get followers faild";
                    throw new RuntimeException(e);
                }
            }
            else if (method.equals("POST")) {
                String followerID = splittedPath[2];
                String followedID = splittedPath[3];

                try {
                    followController.addFollow(followerID, followedID);
                    response = "add follow done";
                } catch (SQLException e) {
                    response = "add follow problem";
                    throw new RuntimeException(e);
                }
            } else if (method.equals("DELETE")) {
                String followerID = splittedPath[2];
                String followedID = splittedPath[3];

                try {
                    followController.deleteFollow(followerID, followedID);
                    response = "delete follow done";
                } catch (SQLException e) {
                    response = "delete follow problem";
                    throw new RuntimeException(e);
                }
            }
        }

        if (splittedPath[1].equals("following")) {
            if (splittedPath.length == 2) {

            } else {
                if (method.equals("GET")) {
                    String userID = splittedPath[2];
                    try {
                        response = new Gson().toJson(followController.getFollowings(userID));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }


        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
