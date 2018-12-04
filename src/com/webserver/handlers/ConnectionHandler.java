package com.webserver.handlers;

import java.net.Socket;
import java.util.List;
import java.util.stream.Stream;

public interface ConnectionHandler {
    void handle(List<String> requestLines, Socket socket);
}
