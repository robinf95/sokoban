package sokoban;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static Socket client;
    public Server() {}

    void connect() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);
        client = serverSocket.accept();
        String msg = read();
        System.out.println(msg);
    }

    String read() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        char[] buffer = new char[200];
        int num = in.read(buffer, 0, 200);

        return new String(buffer, 0, num);
    }

    void write(String str) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.print(str);
        out.flush();
    }
}
