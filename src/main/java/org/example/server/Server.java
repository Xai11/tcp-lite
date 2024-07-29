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
    private ServerSocket serverSocket;
    private ReentrantLock locker = new ReentrantLock();
    private Database database = new Database();

    public Server(ServerSocket serverSocket, int numb) throws IOException {
        this.serverSocket = serverSocket;
        this.number_stream = numb;
    }

    // Метод для вывода сообщения отправленного клиентом
    public void displayData(Scanner input, int number_stream, Socket server) {
        String data = "";
        while(input.hasNext()) {
            data = input.nextLine();
            break;
        }
        database.updateTable("client_info", server, data);
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
                    System.out.println("Клиент " + server.getInetAddress() + " подключился");
                    // Определение output и input потоков общения сокета
                    output = new PrintWriter(server.getOutputStream());
                    input = new Scanner(server.getInputStream());

                    // Сообщение о подключении к серверу
                    commitConnection(output);

                    // Ожидание сообщения от клиента
                    displayData(input, number_stream, server);
                }
                else {
                    System.out.println("Клиент " + server.getInetAddress()
                            + " не смог подключиться");
                }
                // Отключение соединения  с клиентом
                System.out.println("Клиент " + server.getInetAddress() + " отключился");
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
