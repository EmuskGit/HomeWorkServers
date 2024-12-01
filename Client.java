package Lesson3month.Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private BufferedReader in;
    private BufferedWriter out;

    public Client(InputStream in, OutputStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void ping(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }

    public String getResult() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        in.close();
        out.close();
    }
}
