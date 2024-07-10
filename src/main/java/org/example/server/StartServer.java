package org.example.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartServer {
    public static void main(String[] args) {
        Server server = new Server();
        Thread thread = new Thread(server);
        try{
            thread.start();
        } catch (RuntimeException error) {
            error.printStackTrace();
        }

    }
}
