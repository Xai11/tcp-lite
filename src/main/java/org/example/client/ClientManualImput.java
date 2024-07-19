package org.example.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientManualImput {
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

            // Прием приветствия от сервера
            displayData(input);

            // Ручной ввод сообщения для отправки на сервер
            sendMessageToServer(output);

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
    // Метод для ручного ввода сообщения для отправления на сервер
    public static void sendMessageToServer(PrintWriter out) {
        System.out.print("Введите сообщение: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        out.println(str);
        out.flush();
    }


}
