import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServerSide {
    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public ServerSide(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String fromClient;

            // Thread to send out server message
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String serverMessage = scanner.nextLine();
                    out.println("[Server]: " + serverMessage);
                }
            }).start();

            while ((fromClient = in.readLine()) != null) {
                System.out.println(fromClient);
                if (fromClient.equals("bye")) {
                    break;
                }
            }
            System.out.println("Closing connection");
            System.exit(0);
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        new ServerSide(8080);
    }
}