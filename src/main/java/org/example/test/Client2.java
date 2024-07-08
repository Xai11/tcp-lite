package org.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            sleep(10000);
            System.out.println("Message from server: " + message);
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
