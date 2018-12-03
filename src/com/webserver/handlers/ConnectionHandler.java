package com.webserver.handlers;

import java.net.Socket;
import java.util.stream.Stream;

public interface ConnectionHandler {
    void handle(Stream<String> requestLines, Socket socket);
}
