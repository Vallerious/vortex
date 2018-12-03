package com.webserver.server;

import com.webserver.handlers.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {
    protected ServerSocket serverSocket;
    protected Socket socket;

    protected Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public abstract void run() throws IOException;
}
