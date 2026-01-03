import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ClientSide {
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public ClientSide(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String fromClient;

            // Thread to handle incoming messages
            new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println(serverResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Read messages from the console and send to the server
            Scanner scanner = new Scanner(System.in);
            while (true) {
                fromClient = scanner.nextLine();
                out.println("[Client]: " + fromClient);
                if(fromClient.equals("bye")) {
                    break;
                }
            }
            System.exit(0);
        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        new ClientSide("127.0.0.1", 8080);
    }
}