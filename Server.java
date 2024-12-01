package Lesson3month.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<String> strings = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Введите два числа и операцию, которую нужно провести: ");
            readRequest(socket);
            double result = sumRequest();
            System.out.println("Результат: " + result);
            sendRequest(socket,result);
        }
    }

    private static void readRequest(Socket socket) throws IOException {
        strings.clear();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str;
        while ((str = reader.readLine()) != null && !str.isEmpty()) {
            strings.add(str);
            System.out.println("Введённое значение: " + str);
        }
    }

    private static void sendRequest(Socket socket, double result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("Резултать: " + result);
        writer.flush();
    }

    private static double sumRequest() {
        double result = 0;

        try {
            double num1 = Double.parseDouble(strings.get(0));
            double num2 = Double.parseDouble(strings.get(1));
            String operation = strings.get(2);

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new ArithmeticException("Деление на ноль невозможно!");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестная операция: " + operation);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при парсинге чисел: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Ошибка арифметики: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
