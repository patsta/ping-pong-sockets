#!/usr/bin/env python3

import socket

HOST = '127.0.0.1'  # Standard loopback interface address (localhost)
PORT = 65432        # Port to listen on (non-privileged ports are > 1023)

def main():
    print(f'Creating Socket {HOST}, {PORT}')
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    server.bind((HOST, PORT))
    print("Socket is listening")
    server.listen()
    while True:
        print("Waiting for a client ....")
        data = server.recv(1024)
        print(data)


if __name__ == "__main__":
    main()