package org.example.server;


import java.io.IOException;
import java.net.ServerSocket;

public class StartServer {
    public static void main(String[] args) throws IOException {
        final int PORT = 9999;

        // Создаем серверный сокет
        ServerSocket serverSocket = new ServerSocket(PORT);

        Server[] server = new Server[5];
        Thread[] thread = new Thread[5];

        // Создаем потоки сервера
        for(int i = 0; i < 3; i++) {
            server[i] = new Server(serverSocket, i + 1);
            thread[i] = new Thread(server[i]);
            thread[i].start();
            System.out.println("Запуск потока № " + (i + 1));
        }

//        server[0] = null;
//        thread[0].interrupt();

    }
}
