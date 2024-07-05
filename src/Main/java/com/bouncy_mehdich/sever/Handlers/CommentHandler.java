package com.bouncy_mehdich.sever.Handlers;

import com.bouncy_mehdich.sever.Controllers.CommentController;
import com.bouncy_mehdich.sever.Controllers.PostController;
import com.bouncy_mehdich.sever.Models.Comment;
import com.bouncy_mehdich.sever.Models.Post;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

public class CommentHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        CommentController commentController = new CommentController();
        String response = null;


        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        if (method.equals("GET")) {
            String postID = splittedPath[2];

            try {
                response = commentController.getCommentsOfPost(postID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (method.equals("POST")) {
            InputStream requestBody = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            requestBody.close();

            String newComment = body.toString();
            JSONObject obj = new JSONObject(newComment);

            Comment comment = new Comment(obj.getString("postID"), obj.getString("text"), obj.getString("senderID"), new Date(obj.getString("commentDate")));

            try {
                commentController.addComment(comment, null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());

        os.close();
    }
}
