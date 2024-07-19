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
    private static Scanner input;

    public static void main(String[] args) throws IOException {
        try{
            client = new Socket(SERVER_NAME, PORT);
            // Клиент соединился с сервером

            out = new PrintWriter(client.getOutputStream());

            // Ну вот здесь же должен вывести на сервере, ну до этого же работало....
            out.println("Клиент тут!");
            out.flush();
            input = new Scanner(client.getInputStream());
            displayData(input);
            sleep(1000);



            // Отправка сообщений (должна быть)
            // Но пока что убрана.

            client.close();
        } catch (IOException error) {
            error.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
