package com.webserver;

import com.webserver.server.HttpServer;
import com.webserver.server.Server;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Server myServer = new HttpServer(8111);

        myServer.run();
    }
}
