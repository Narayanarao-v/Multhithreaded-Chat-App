package com.chatapp.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    static Vector<ClientHandler> clients = new Vector<>();
    static int clientId = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("🔵 Server started on port 1234");

        while (true) {
            Socket socket = serverSocket.accept();
            String clientName = "Client" + clientId++;
            System.out.println("✅ " + clientName + " connected: " + socket);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            ClientHandler client = new ClientHandler(socket, clientName, dis, dos);
            clients.add(client);

            Thread thread = new Thread(client);
            thread.start();
        }
    }
}
