package org.example.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;

public class StartServer {
    public static void main(String[] args) throws IOException {
        final int PORT = 9999;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Server[] server = new Server[5];
        Thread[] thread = new Thread[5];
        for(int i = 0; i < 5; i++) {
            server[i] = new Server(serverSocket, i);
            thread[i] = new Thread(server[i]);
            thread[i].start();
        }


    }
}
