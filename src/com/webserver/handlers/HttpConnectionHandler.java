package com.webserver.handlers;

import java.net.Socket;
import java.util.stream.Stream;

public class HttpConnectionHandler implements ConnectionHandler, Runnable {
    private Stream<String> requestLines;
    private Socket socket;

    public HttpConnectionHandler(Stream<String> requestLines, Socket socket) {
        this.requestLines = requestLines;
        this.socket = socket;
    }

    /**
     * Used for Synchronous execution of the handler.
     * 
     */
    @Override
    public void handle(Stream<String> requestLines, Socket socket) {
        requestLines.forEach(System.out::println);
    }

    @Override
    public void run() {
        this.handle(this.requestLines, this.socket);
    }
}
