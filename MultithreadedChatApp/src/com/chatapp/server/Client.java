package com.chatapp.server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("localhost", 1234);
            System.out.println("🟢 Connected to chat server!");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Sending messages
            Thread sendThread = new Thread(() -> {
                while (true) {
                    String msg = scanner.nextLine();
                    try {
                        dos.writeUTF(msg);
                        if (msg.equalsIgnoreCase("logout")) {
                            socket.close();
                            break;
                        }
                    } catch (IOException e) {
                        break;
                    }
                }
            });

            // Receiving messages
            Thread readThread = new Thread(() -> {
                while (true) {
                    try {
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        break;
                    }
                }
            });

            sendThread.start();
            readThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
