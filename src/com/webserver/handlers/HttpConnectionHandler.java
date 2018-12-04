package com.webserver.handlers;

import com.webserver.objects.Request;
import com.webserver.parsers.RequestParser;

import java.net.Socket;
import java.util.List;
import java.util.stream.Stream;

public class HttpConnectionHandler implements ConnectionHandler, Runnable {
    private List<String> requestLines;
    private Socket socket;
    private RequestParser requestParser;

    public HttpConnectionHandler(List<String> requestLines, Socket socket, RequestParser reqParser) {
        this.requestLines = requestLines;
        this.socket = socket;
        this.requestParser = reqParser;
    }

    /**
     * Used for Synchronous execution of the handler.
     *
     */
    @Override
    public void handle(List<String> requestLines, Socket socket) {
        Request req = this.requestParser.parse(requestLines);
    }

    @Override
    public void run() {
        this.handle(this.requestLines, this.socket);
    }
}
