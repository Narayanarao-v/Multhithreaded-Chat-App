package com.chatapp.server;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final String name;
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final Socket socket;
    private boolean isLoggedIn;

    public ClientHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos) {
        this.socket = socket;
        this.name = name;
        this.dis = dis;
        this.dos = dos;
        this.isLoggedIn = true;
    }

    @Override
    public void run() {
        String received;
        try {
            while (true) {
                received = dis.readUTF();

                if (received.equalsIgnoreCase("logout")) {
                    System.out.println("❌ " + name + " disconnected.");
                    this.isLoggedIn = false;
                    this.socket.close();
                    break;
                }

                System.out.println("📨 " + name + " says: " + received);

                for (ClientHandler client : Server.clients) {
                    if (client != this && client.isLoggedIn) {
                        client.dos.writeUTF(name + ": " + received);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ " + name + " connection lost.");
        } finally {
            try {
                dis.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
