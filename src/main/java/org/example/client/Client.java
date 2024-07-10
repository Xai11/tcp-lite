package org.example.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.Thread.sleep;

public class Client  {
    private static final String SERVER_NAME = "127.0.0.1";
    private static final int PORT = 9999;
    private static Socket client;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        try{
            client = new Socket(SERVER_NAME, PORT);
            // Клиент соединился с сервером
            out = new PrintWriter(client.getOutputStream());

            out.println("Huy");
            out.flush();

            client.close();
        } catch (IOException error){
            error.printStackTrace();
        }


         //Отправка сообщений



    }
    public static void getChoiceOption() {
        System.out.println("Введите вариант");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
    }
    public static void displayData(Scanner input) {
        while(input.hasNext()) {
            System.out.println(input.nextLine());
        }
    }
    public static void sendMessageToServer(PrintWriter out) {
        System.out.print("Введите сообщение: ");
        out.flush();
    }


}
