
import java.net.*;
import java.io.*;

public class KnockKnockServer {
    public int portNumber;
    public static void main(String[] args) throws IOException {
        new KnockKnockServer(8080);
    }

    public KnockKnockServer(int portNumber) {

        this.portNumber = portNumber;

        try (
            // Initilization will fail if port is already being used
            ServerSocket serverSocket = new ServerSocket(portNumber);
            // This .accept is a waiting call, i.e it waits until a request comes
            Socket clientSocket = serverSocket.accept();


            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {

            String inputLine, outputLine;

            // Initiate conversation with client by saying "knock!knock!"
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}