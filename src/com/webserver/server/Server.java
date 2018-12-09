package com.webserver.server;

import com.webserver.constants.WebConstants;
import com.webserver.handlers.ConnectionHandler;
import com.webserver.handlers.PostRequestHandler;
import com.webserver.handlers.RequestHandlerFactory;
import com.webserver.parsers.HttpRequestParser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.FutureTask;

public class Server {
    private ServerSocket serverSocket;
    private int port;
    private RequestHandlerFactory requestHandlerFactory;

    public Server(int port) {
        this.port = port;
        this.requestHandlerFactory = new RequestHandlerFactory();
    }

    public void run() throws IOException {
        System.out.println("Server listening on port: " + this.port);
        this.serverSocket = new ServerSocket(this.port);
        this.serverSocket.setSoTimeout(WebConstants.SOCKET_TIMEOUT_MILLISECONDS);

        while (true) {
            try (Socket clientSocket = this.serverSocket.accept()) {
                clientSocket.setSoTimeout(WebConstants.SOCKET_TIMEOUT_MILLISECONDS);
                // This is like a promise, but we do not need any value to return so
                // we could have used something else.
                FutureTask task = new FutureTask(
                        new ConnectionHandler(clientSocket, new HttpRequestParser(), this.requestHandlerFactory)
                        , null
                );

                task.run();
            } catch(SocketTimeoutException ste) {
                // Print something about socket timeout.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerRoute(String route, PostRequestHandler postRequestHandler) {
        this.requestHandlerFactory.registerHandler(route, postRequestHandler);
    }
}
