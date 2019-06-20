package sokoban;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    
    public Client(Socket socket) {
        this.socket = socket;
    }

    void connect(String msg) throws IOException {
        write(socket, msg);
        String received = read(socket);
        System.out.println(received);
        
    }
    
    public static void write(Socket socket, String msg) throws IOException {
        while (true) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.print(msg);
            System.out.println("MESSAGE: " + msg);
            out.flush();
        }
    }
    
    String read(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] buffer = new char[200];
        int num = in.read(buffer, 0, 200);
        return new String(buffer, 0, num);
    }
    
}
