package org.example.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;

import static java.lang.Thread.sleep;

public class StartServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        final int PORT = 9999;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Server[] server = new Server[5];
        Thread[] thread = new Thread[5];
        for(int i = 0; i < 3; i++) {
            server[i] = new Server(serverSocket, i);
            thread[i] = new Thread(server[i]);
            thread[i].start();
            System.out.println("+-" + i);
        }
        sleep(10000);

//        server[1] = null;
//        server[2] = null;
//        thread[0].interrupt();
//        server[0] = null;
//        System.out.println("+-" + 0);
//        thread[1].wait();
//        thread[2].wait();
    }
}
