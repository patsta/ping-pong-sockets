import java.net.*;
import java.util.concurrent.TimeUnit;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SocketsClient4{
    public static void main( String[] args ) {
        String serverHost = "127.0.0.1";

        for(int i = 1; i < 11; i++) {
            try {
                // Timout of 2 second
                System.out.println("Waiting 2 seconds to connect...");
                TimeUnit.SECONDS.sleep(2);

                // close existing sockets and streams
                ObjectOutputStream outputToServer = null;
                ObjectInputStream inputFromServer = null;
                Socket socketsServer=null;

                // Create new sockets and streams
                socketsServer = new Socket(serverHost, 65007 );
                System.out.println("Connection to server started ....");
                outputToServer = new ObjectOutputStream( socketsServer.getOutputStream() );
                inputFromServer = new ObjectInputStream( socketsServer.getInputStream() );


                // Send 10 times PING
                for(int x = 1; x <= 10; x++) {
                    try {
                        Object clientMessage = new String( "PING" );
                        outputToServer.writeObject(clientMessage);
                        // Print client-message
                        System.out.println("Client sent: " + clientMessage);
                        Object serverMessage = inputFromServer.readObject();
                        // Print server-message
                        System.out.println("Client received: " + serverMessage);
                        TimeUnit.SECONDS.sleep(1);
                        // print exception
                    }catch(Exception e){
                        System.out.println("Connection loss.");
                        break;
                    }

                    if(x > 9) {
                        i = 12;
                        continue;
                    }

                }

                // Close output stream
                outputToServer.close();

                // Close input stream
                inputFromServer.close();

                // Close socket
                socketsServer.close();
                socketsServer.close();
                System.out.println("Successfully finished");

            }catch(Exception e){
                System.out.println("Retry to connect");

            }
        }
    }
}