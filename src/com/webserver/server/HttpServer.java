package com.webserver.server;

import com.webserver.handlers.HttpConnectionHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpServer extends Server {
    public HttpServer(int port) throws IOException {
        super(port);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            this.socket = this.serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            new Thread(new HttpConnectionHandler(in.lines(), this.socket)).start();
        }
    }
}
