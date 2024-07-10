package org.example.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/*
 * Это пока что тестовый класс.
 * Он нужен для имитации нескольких клиентов.
 * Его код может отличаться от главного класса Client.
 */
public class ClientTwo {
    private static final String SERVER_NAME = "127.0.0.1";
    private static final int PORT = 8081;
    private static PrintWriter input;
    private static Socket client;

    public static void main(String[] args) throws Exception {
        client = new Socket(SERVER_NAME, PORT);

        // Это нужно будет убрать или засунуть в отдельный метод
        Scanner in = new Scanner(client.getInputStream());
        displayData(in);

        // Вот этот вот блок раньше работал, а щас то че.... :(
//        PrintWriter out = new PrintWriter(client.getOutputStream());
//
//        for(int i = 0; i < 10; i++) {
//            out.println(i);
//            out.flush();
//        }
//        out.close();


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
    static void inputDataToServer() {

    }

}
