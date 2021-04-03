#!/usr/bin/env python3

import socket

HOST = '127.0.0.1'  # The server's hostname or IP address
PORT = 65432        # The port used by the server


client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
i = 0
client.connect((HOST, PORT))
while True:
    client.sendall(b"Ping")
    #i = i + 1




