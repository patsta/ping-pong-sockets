
import java.net.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class SocketsServer {
    public static void main (String args[]) {
        // Define socket variable
        ServerSocket serverSocket = null;
        Integer serverPort = 65007;
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server started on port: " + serverSocket.getLocalPort());
            System.out.println("Waiting for connections...");
            Socket client = serverSocket.accept();
            System.out.println("Client accepted: " + client.getRemoteSocketAddress());
            // Create Input and Output Streams
            ObjectOutputStream outputToClient = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inputFromClient = new ObjectInputStream(client.getInputStream());

            // Receive 10 times PING and response with PONG

            for (int i = 0; i <= 9; i++) {
                System.out.println("Waiting for data...");
                Object ServerMessage = inputFromClient.readObject();
                System.out.println("Received from client: " + ServerMessage);

                // Prepare response message
                ServerMessage = new String("Pong");
                outputToClient.writeObject(ServerMessage);
                System.out.println("Server-Message: " + ServerMessage);

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