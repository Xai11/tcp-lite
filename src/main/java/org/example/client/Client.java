package org.example.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_NAME = "127.0.0.1";
    private static final int PORT = 8081;

    public static void main(String[] args) throws Exception {
        Socket client = new Socket(SERVER_NAME, PORT);

        Scanner in = new Scanner(client.getInputStream());
        displayData(in);
        in.close();

        PrintWriter out = new PrintWriter(client.getOutputStream());

        for(int i = 0; i < 10; i++) {
            out.println(i);
            out.flush();
        }
        out.close();


        client.close();
    }
    static void getChoiceOption() {
        System.out.println("Введите вариант");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
    }
    static void displayData(Scanner input) {
        while(input.hasNext()) {
            System.out.println(input.nextLine());
        }
    }
} // я тут
