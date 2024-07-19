package org.example.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.interrupted;

public class Server implements Runnable {
    private int number_stream;
    private final static int PORT = 9999;
    private final static String SERVER_URL = "www.tcptestproject.com";
    public PrintWriter output;
    private Scanner input;
    ServerSocket serverSocket;
    private ReentrantLock locker = new ReentrantLock();

    public Server(ServerSocket serverSocket, int numb) throws IOException {
        this.serverSocket = serverSocket;
        this.number_stream = numb;
    }

    // Метод для вывода сообщения отправленного клиентом
    public static void displayData(Scanner input, int number_stream) {
        while(input.hasNext()) {
            System.out.print("Поток № " + number_stream + " ответ. ");
            System.out.println(input.nextLine());
            break;
        }
    }

    // Метод для отправки приветствия клиенту
    public void commitConnection(PrintWriter out) {
        out.println("Вы подключены к " + SERVER_URL);
        out.flush();
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
                // Ловим соединение клиента с сервером
                Socket server = serverSocket.accept();
                locker.lock();

                // Проверка подключения
                if (server.isConnected()) {
                    System.out.println("Поток № " + number_stream + " Соединение установленно.");

                    // Определение output и input потоков общения сокета
                    output = new PrintWriter(server.getOutputStream());
                    input = new Scanner(server.getInputStream());

                    // Сообщение о подключении к серверу
                    commitConnection(output);

                    // Ожидание сообщения от клиента
                    displayData(input, number_stream);

                    System.out.println("Поток № " + number_stream + ". Клиент отключился! Поток свободен");
                }
                else {
                    System.out.println("Клиент не смог подключиться к потоку № " + number_stream);
                }
                // Отключение соединения  с клиентом
                server.close();

            } catch (RuntimeException error) {
                error.printStackTrace();

            } catch (IOException error) {
                error.printStackTrace();
            } finally {
                locker.unlock();
            }
        }
    }
}
