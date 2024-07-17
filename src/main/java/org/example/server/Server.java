package org.example.server;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.interrupted;

public class Server implements Runnable {
    private int numb;
    private final static int PORT = 9999;
    private final static String SERVER_URL = "www.tcptestproject.com";
    private static List<Socket> USERS = new ArrayList<>();
    public PrintWriter out;
    private Scanner input;
    ServerSocket serverSocket;
    private ReentrantLock locker = new ReentrantLock();

    public Server(ServerSocket serverSocket, int numb) throws IOException {
        this.serverSocket = serverSocket;
        this.numb = numb;
    }

    public void postChoiceOption() {
    }

    public static void displayData(Scanner input) {
        while(input.hasNext()) {
            System.out.println(input.nextLine());
            break;
        }
    }
    public void commitConnection(PrintWriter out) {
        out.println("Вы подключены к " + SERVER_URL);
        out.flush();
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
//                while (true) {
                // Создаем рабочий сокет

                // Ловим соединение клиента с сервером
                Socket server = serverSocket.accept();
                locker.lock();

                if (server.isConnected()) {
                    System.out.print("Я поймал соединение!");
                    System.out.print(" Я поток №" + numb);
                }
                out = new PrintWriter(server.getOutputStream());
                Scanner in = new Scanner(server.getInputStream());
                commitConnection(out);
                displayData(in);
                // Сообщение о подключении к серверу

                server.close();

//                }

            } catch (RuntimeException error) {
                error.printStackTrace();

            } catch (IOException error) {
                error.printStackTrace();
            } finally {
                locker.unlock();
            }


            //        Scanner in = new Scanner(System.in);
            //        displayData(in, out);
        }
    }
    public void queryChoiceOption(PrintWriter out) {
        out.println("Вам доступны следующие действия:");
        out.println("Введите /c, чтобы отправить сообщение на сервер.");
        out.println("Введите /q, чтобы покинуть сервер.");
        out.flush();
    }

    public void addSocketToUSERS(Socket server) throws IOException {

    }
    public void sendMessageToClient(PrintWriter out) {
        System.out.print("Введите сообщение: ");
        input = new Scanner(System.in);
        while(input.hasNext()){
            out.println("Сообщение от сервера: " + input.nextLine());
            out.flush();
        }

    }
}
