package sokoban;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    
    Client(Socket socket) {
        this.socket = socket;
    }
    
    void write(Socket socket, String msg) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.print(msg);
        out.flush();
    }
    
    String read(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] buffer = new char[200];
        int num = in.read(buffer, 0, 200);
        return new String(buffer, 0, num);
    }
    
}
