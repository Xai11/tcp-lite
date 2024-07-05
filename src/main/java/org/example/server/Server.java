package org.example.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    private static final int PORT = 8081;
    private static final String SERVER_URL = "www.tcptestproject.com";
    private static HashMap<SocketAddress, String> hostUrl = new HashMap<>();
    private static PrintWriter out;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket server = serverSocket.accept();

        hostUrl.put(server.getLocalSocketAddress(), SERVER_URL);

        out = new PrintWriter(server.getOutputStream());
        out.println("Вы подключены к " + hostUrl.get(server.getLocalSocketAddress()));



        Scanner in = new Scanner(System.in);
        displayData(in, out);

        out.close();
        in.close();
        server.close();
        serverSocket.close();

    }
    static void postChoiceOption() {

    }

    static void displayData(Scanner input, PrintWriter out) {
        while(input.hasNext()) {
            out.println(input.nextLine());
            out.flush();
        }
    }
}
