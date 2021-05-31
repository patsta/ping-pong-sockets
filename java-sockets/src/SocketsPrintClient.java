import java.net.*;
import java.util.concurrent.TimeUnit;
import java.io.*;


public class SocketsPrintClient{ // CLIENT
    public static void main( String[] args ) {
        String serverHost = "127.0.0.1";
        Integer serverPort = 65007;

        try {
            Socket pongServer= new Socket(serverHost, serverPort );
            System.out.println("Establishing connection to server ....");

            // Define buffer and PrintWriter for input and output
            PrintWriter outFromClient= new PrintWriter( pongServer.getOutputStream(), true );
            BufferedReader inputFromServer =														// Verbindung mit Socket imput Stream
                    new BufferedReader(
                            new InputStreamReader(pongServer.getInputStream()));

            for(int i = 1; i<=10; i++) {
                // Send data to server
                Object message = new String(i + ": PING");
                outFromClient.println(message);

                System.out.println("Client-Message: " + message);

                Object receivedServerMessage = inputFromServer.readLine();
                System.out.println("Following server message received: " + receivedServerMessage);
                System.out.println(receivedServerMessage);


                TimeUnit.SECONDS.sleep(1);

            }

            outFromClient.close(); inputFromServer.close(); pongServer.close(); //Str�me und Socket schlie�en


        }catch (Exception e) {
            System.out.println("Client-Error: " + e);
        }

    }
}
