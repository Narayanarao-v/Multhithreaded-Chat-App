# Multithreaded Chat Application

This is a Java-based multithreaded chat system using sockets. It supports multiple clients connecting to a single server and chatting in real-time.

## Features
- Server handles multiple clients using threads
- Real-time message broadcasting
- Command to logout
- Simple terminal-based interface

## How to Run
1. Compile all Java files:
    ```bash
    javac com/chatapp/server/*.java
    ```

2. Run the server:
    ```bash
    java com.chatapp.server.Server
    ```

3. Open new terminal(s) and run the client:
    ```bash
    java com.chatapp.server.Client
    ```

Type `logout` to disconnect from the server.

