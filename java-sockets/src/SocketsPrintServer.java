
import java.io.*;
import java.net.*;


public class SocketsPrintServer {
    public static void main (String args[]) {
        // Define socket variable
        ServerSocket serverSocket = null;
        Integer serverPort = 65007;
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server started on port: " + serverSocket.getLocalPort());
            System.out.println("Waiting for connections...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client accepted: " + clientSocket.getRemoteSocketAddress());

            // Create Input and Output Streams
            PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));

            // Receive 10 times PING and response with PONG

            for (int i = 1; i <= 10; i++) {
                System.out.println("Waiting for data...");
                Object message = inputFromClient.readLine();
                System.out.println("Received from client: " + message);

                // Prepare response message
                message = new String("Pong");
                outputToClient.println(message);
                System.out.println("Server-Message: " + message);

            }

            // Close InputStream
            inputFromClient.close();

            // Close OutputSteam
            outputToClient.close();

            // Close Socket
            System.out.println("Server stoppped");
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Server error");
        }
    }
}