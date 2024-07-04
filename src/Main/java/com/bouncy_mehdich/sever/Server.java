package com.bouncy_mehdich.sever;


import java.io.IOException;
import java.net.InetSocketAddress;


import com.sun.net.httpserver.HttpServer;
import com.bouncy_mehdich.sever.Handlers.*;

public class Server {

    public static void main(String[] args){
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);

            server.createContext("/user", new UserHandler());
            server.createContext("/login", new UserHandler());

            server.createContext("/profile", new ProfileHandler());
            server.createContext("/post", new PostHandler());
            server.createContext("/following", new FollowsHandler());


            server.start();


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
