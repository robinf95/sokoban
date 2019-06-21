package sokoban;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;

    Client(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            write("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(String msg) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.print(msg);
        out.flush();
    }

    String read() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] buffer = new char[200];
        int num = in.read(buffer, 0, 200);
        return new String(buffer, 0, num);
    }

}
