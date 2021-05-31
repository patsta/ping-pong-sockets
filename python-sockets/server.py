#! /usr/local/bin/python

import socket
import struct
import time

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
            time.sleep(1)
            data = clientsocket.recv(1024)
            if not data:
                break

            print("Client send: " + repr(data))
            clientsocket.sendall(str(i) + " : Pong")

        clientsocket.close()
        serversocket.close()

    except Exception as exp:
        print(exp)

if __name__ == "__main__":
    main()