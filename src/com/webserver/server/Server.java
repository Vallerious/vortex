package com.webserver.server;

import com.webserver.handlers.ConnectionHandler;
import com.webserver.handlers.RequestHandler;

import javax.lang.model.type.NullType;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.FutureTask;

public class Server {
    private static final int SOCKET_TIMEOUT_MILLISECONDS = 200;
    private ServerSocket serverSocket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        this.serverSocket = new ServerSocket(this.port);
        this.serverSocket.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

        while (true) {
            try (Socket clientSocket = this.serverSocket.accept()) {
                clientSocket.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket, new RequestHandler());

                // This is like a promise, but we do not need any value to return so
                // we could have used something else.
                FutureTask task = new FutureTask(connectionHandler, null);
                task.run();
            } catch(SocketTimeoutException ste) {
                // Print something about socket timeout.
                ste.printStackTrace();
            }
        }
    }
}
