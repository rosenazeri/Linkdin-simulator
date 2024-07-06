package Main.java.com.bouncy_mehdich.sever.Handlers;

import Main.java.com.bouncy_mehdich.sever.Controllers.JobController;
import Main.java.com.bouncy_mehdich.sever.Controllers.UserController;
import Main.java.com.bouncy_mehdich.sever.Models.CertainJobs;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;

public class JobsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        JobController jobController = new JobController();
        String response = null;


        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        if (method.equals("POST")) {
            InputStream requestBody = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            requestBody.close();

            String newJob = body.toString();
//                Gson gson = new Gson();
//                User user = gson.fromJson(newUser, User.class);
            JSONObject jsonObject = new JSONObject(newJob);
            try {
                jobController.addCertainJob(splittedPath[2],new CertainJobs(jsonObject.getString("title"), jsonObject.getString("employment"), jsonObject.getString("company"), jsonObject.getString("workPlace"), jsonObject.getString("workPlaceType"), null, null, jsonObject.getString("explanation"), jsonObject.getString("skills")));
                response = "ok";
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());

            os.close();

        }
    }
}
