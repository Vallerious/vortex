package com.webserver.server;

import com.webserver.handlers.HttpConnectionHandler;
import com.webserver.parsers.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HttpServer extends Server {
    public HttpServer(int port) throws IOException {
        super(port);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            this.socket = this.serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            HttpConnectionHandler connHandler = new HttpConnectionHandler(
                    in.lines().collect(Collectors.toList()),
                    this.socket,
                    new RequestParser()
            );

            new Thread(connHandler).start();
        }
    }
}
