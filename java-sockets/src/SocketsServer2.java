import java.net.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SocketsServer2{
    public static void main(String[] args) {
        ServerSocket serverSockets = null;

        // Trying to open 10 times sockets and streams
        for ( int i = 1; i < 11; i++) {
            try {

                // close existing sockets and streams
                ObjectOutputStream outputToClient = null;
                ObjectInputStream inputFromClient = null;
                serverSockets = null;
                Socket clientSocket = null;

                // create new sockets and streams
                serverSockets = new ServerSocket (65007);
                System.out.println("Server started ... [" + serverSockets.getLocalPort() + "]");
                clientSocket = serverSockets.accept();
                System.out.println("Client accepted ... [" + clientSocket.getRemoteSocketAddress() + "]");
                outputToClient = new ObjectOutputStream (clientSocket.getOutputStream() );
                inputFromClient = new ObjectInputStream (clientSocket.getInputStream());

                // Receive 10 times PING and response with PONG
                for (int x = 1; x <=10; x++) {
                    try {
                        Object serverMessage = inputFromClient.readObject();
                        System.out.println("Server received: " + serverMessage);
                        serverMessage = new String("PONG");
                        outputToClient.writeObject (serverMessage);
                        System.out.println("Server sent: " + serverMessage);

                        // if loop passed 9 times -> close sockest and streams
                        if (x > 9) {

                            inputFromClient.close();
                            outputToClient.close();
                            clientSocket.close();
                            serverSockets.close();
                            System.out.println("Successfully finished");
                            i=12;
                            break;
                        }

                        // Close streams and connection when exception received
                    }catch(Exception e){
                        System.out.println("Connection loss");
                        inputFromClient.close();
                        outputToClient.close();
                        clientSocket.close();
                        serverSockets.close();
                        i=1;
                        break;
                    }
                }
                // Close Streams and sockets
                inputFromClient.close();
                outputToClient.close();
                clientSocket.close();
                serverSockets.close();

            }catch (Exception e) {
                System.out.println("Das war der " + i + " Versuch");
                }
        } // for
    }
}