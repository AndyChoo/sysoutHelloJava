import java.net.*;
import java.io.*;

public class ClientSide {
    private Socket socket = null;
    private DataOutputStream out = null;
    private BufferedReader in = null;
    private BufferedReader echo = null;

    public ClientSide(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            in = new BufferedReader(new InputStreamReader(System.in));
            echo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            // Start a thread to handle incoming messages
            new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = echo.readLine()) != null) {
                        System.out.println(serverResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }

        String line = "";
        while (!line.equals("bye")) {
            try {
                line = in.readLine();
                out.writeUTF(line);
            } catch(IOException i) {
                System.out.println(i);
            }
        }

        try {
            in.close();
            out.close();
            echo.close();
            socket.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        new ClientSide("127.0.0.1", 8080);
    }
}