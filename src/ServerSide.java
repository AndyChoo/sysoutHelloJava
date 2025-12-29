import java.net.*;
import java.io.*;

public class ServerSide {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private PrintWriter out;

    public ServerSide(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println("echo from server...");
            String line = "";
            while (!line.equals("bye")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                    // out.println("echo from server: " + line);
                } catch(IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            socket.close();
            in.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        new ServerSide(8080);
    }
}