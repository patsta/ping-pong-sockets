#!/usr/bin/env python3

import socket
import struct
import time

def main():
    try:
        serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        addr = ("127.0.0.1", 65000)

        serverSocket.connect(addr)

        for i in range(1, 11):
            time.sleep(1)
            print("Sending PING")
            serverSocket.send(struct.pack("i", 4))
            serverSocket.send("PING")

            data = serverSocket.recv(4)
            (msg,) = struct.unpack("i", data)
            data = ""
            remaining = int(msg)
            while remaining > 0:
                recieved = serverSocket.recv(remaining)
                remaining = remaining - len(recieved)
                data += recieved

            print("Server response " + data)
        serverSocket.close()
    except Exception as exp:
        print(exp)

if __name__ == "__main__":
    main()


