import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class SocketsPrintClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        Integer serverPort = 65007;
        try {
            Socket pongServer = new Socket(serverAddress, serverPort);
            System.out.println("Establishing connection to server ....");

            // Define buffer and PrintWriter for input and output
            BufferedReader outputFromServer = new BufferedReader(new InputStreamReader(pongServer.getInputStream()));
            PrintWriter inputFromClient = new PrintWriter(pongServer.getOutputStream());

            for(int i = 1; i <= 10; i++) {
                Object message = new String(i + ": PING");
                // Send data to server
                inputFromClient.println(message);

                System.out.println("Client-Message: " + message);

                Object receivedServerMessage = outputFromServer.readLine();
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
