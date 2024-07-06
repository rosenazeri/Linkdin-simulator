package Main.java.com.bouncy_mehdich.sever.Handlers;

import Main.java.com.bouncy_mehdich.sever.Models.Post;
import Main.java.com.bouncy_mehdich.sever.Controllers.PostController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

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

            String newPost = body.toString();
            JSONObject obj = new JSONObject(newPost);

            Post post = new Post(obj.getString("postID"), obj.getString("postCaption"), obj.getString("senderID"), null);


            String status = "";
            try {
                postController.addPost(post, null);
                status = "1";
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (status.equals("1")) {
                response = "ok";
            }
            else {
                response = "postSaving problem";
            }
        } else if (method.equals("GET")) {
            response = postController.getAllPosts();
        }

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());

        os.close();


    }
}
