package com.webserver.handlers;

import com.webserver.io.Reader;
import com.webserver.io.Writer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private Socket clientSocket;
    private RequestHandler requestHandler;
    private InputStream clientSocketInputData;
    private OutputStream clientSocketOutputData;

    public ConnectionHandler(Socket clientSocket, RequestHandler requestHandler) throws IOException {
        this.clientSocket = clientSocket;
        this.requestHandler = requestHandler;
        this.clientSocketInputData = this.clientSocket.getInputStream();
        this.clientSocketOutputData = this.clientSocket.getOutputStream();
    }

    @Override
    public void run() {
        try {
            String requestText = Reader.readAllLines(this.clientSocketInputData);
            byte[] responseContent = this.requestHandler.handle(requestText);
            Writer.writeBytes(responseContent, this.clientSocketOutputData);

            this.clientSocketInputData.close();
            this.clientSocketOutputData.close();
            this.clientSocket.close();
        } catch(IOException io) {
            io.printStackTrace();
        }
    }
}
