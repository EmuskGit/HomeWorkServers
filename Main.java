package Lesson3month.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 8088)) {
            Client client = new Client(socket.getInputStream(), socket.getOutputStream());

            System.out.println("Введите два числа и операцию (например: 2 + 2):");
            for (int i = 0; i < 3; i++) {
                String userInput = scanner.nextLine();
                client.ping(userInput);
            }
            String result = client.getResult();
            System.out.println("Результат от сервера: " + result);
            client.close();
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при подключении к серверу: " + e.getMessage());
        }
    }
}
