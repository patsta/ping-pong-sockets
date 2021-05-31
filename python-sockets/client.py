#!/usr/bin/env python3

import socket
import struct
import time


def main():
    try:
        serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        addr = ("127.0.0.1", 65000)

        serverSocket.connect(addr)
        for i in range(1,11):
            time.sleep(1)
            serverSocket.sendall(str(i) + ": Ping")
            data = serverSocket.recv(1024)
            # repr: returns a printable representation of the given object.
            print('Server send: ' +  repr(data))


    except Exception as exp:
        print(exp)

if __name__ == "__main__":
    main()


