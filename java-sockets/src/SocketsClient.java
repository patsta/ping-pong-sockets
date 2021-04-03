import java.net.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

public class SocketsClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        Integer serverPort = 65007;
        try {
            Socket pongServer = new Socket(serverAddress, serverPort);
            System.out.println("Establishing connection to server ....");

            // Define streams  for input and output
            ObjectInputStream outputFromServer = new ObjectInputStream(pongServer.getInputStream());
            ObjectOutputStream inputFromClient = new ObjectOutputStream(pongServer.getOutputStream());

            for(int i = 0; i <= 9; i++) {
                Object clientMessage = new String(i + ": PING");
                // Send data to server
                inputFromClient.writeObject(clientMessage);

                System.out.println("Client-Message: " + clientMessage);

                Object receivedServerMessage = outputFromServer.readObject();
                System.out.println("Following server message received: " + receivedServerMessage);
                TimeUnit.SECONDS.sleep(1);

            }
            // Close input stream
            outputFromServer.close();
            // Close output stream
            inputFromClient.close();
            // Close socket
            pongServer.close();

        } catch (Exception e) {
            System.out.println("Client-Error");
        }
    }
}
