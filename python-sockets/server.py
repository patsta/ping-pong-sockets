#! /usr/local/bin/python

import socket
import struct


def main():
    try:
        print("creating socket")
        serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        print("binding socket to localhost")
        serversocket.bind(("localhost", 65000))

        print("listening")
        serversocket.listen(1)

        # accept connections from outside
        print("waiting for a client to connect...")
        (clientsocket, address) = serversocket.accept()

        for i in range(1,11):
            data = clientsocket.recv(4)
            (msg,) = struct.unpack("i", data)
            data = ""
            remaining = int(msg)
            while remaining > 0:
                recieved = clientsocket.recv(remaining)
                remaining = remaining - len(recieved)
                data += recieved

            print("Client " + data)

            clientsocket.send(struct.pack("i", 4))
            print("Sending PONG")
            # Sending
            clientsocket.send("PONG")

        # Close Sockets
        clientsocket.close()
        serversocket.close()
        print("Server closed")
    except Exception as exp:
        print(exp)

if __name__ == "__main__":
    main()