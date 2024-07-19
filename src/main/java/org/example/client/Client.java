package org.example.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client  {
    private static final String SERVER_NAME = "127.0.0.1";
    private static final int PORT = 9999;
    private static Socket client;
    private static PrintWriter output;
    private static Scanner input;

    public static void main(String[] args) throws IOException {
        try{
            client = new Socket(SERVER_NAME, PORT);
            // Клиент соединился с сервером

            // Определение output и input потоков общения сокета
            output = new PrintWriter(client.getOutputStream());
            input = new Scanner(client.getInputStream());

            // Отправка сообщения на сервер
            output.println("Клиент первый!");
            output.flush();

            // Прием приветствия от сервера
            displayData(input);

            // Отключение клиента
            client.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }
    // Метод для вывода сообщения отправленного сервером
    public static void displayData(Scanner input) {
        while(input.hasNext()) {
            System.out.println(input.nextLine());
            break;
        }
    }
}
