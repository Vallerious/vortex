package com.webserver;

import com.webserver.server.Server;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server(8111);
        server.run();
    }
}
