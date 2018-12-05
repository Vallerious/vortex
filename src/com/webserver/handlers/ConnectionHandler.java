package com.webserver.handlers;

import com.webserver.io.Reader;
import com.webserver.io.Writer;
import com.webserver.objects.Request;
import com.webserver.parsers.HttpRequestParser;
import com.webserver.parsers.RequestParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private static final int CONNECTION_KILL_LIMIT = 5000;

    private Socket clientSocket;
    private InputStream clientSocketInputData;
    private OutputStream clientSocketOutputData;
    private HttpRequestParser requestParser;

    public ConnectionHandler(Socket clientSocket, HttpRequestParser requestParser) throws IOException {
        this.clientSocket = clientSocket;
        this.clientSocketInputData = this.clientSocket.getInputStream();
        this.clientSocketOutputData = this.clientSocket.getOutputStream();
        this.requestParser = requestParser;
    }

    @Override
    public void run() {

        try {
            String requestText = null;

            int connectionReadTimer = 0;

            while(connectionReadTimer++ < CONNECTION_KILL_LIMIT) {
                requestText = Reader.readAllLines(this.clientSocketInputData);

                if(requestText.length() > 0) break;
            }

            Request req = this.requestParser.parse(requestText);
            RequestHandler requestHandler = RequestHandlerFactory.createRequestHandler(req.getMethod());

            byte[] responseContent = requestHandler.handle(req);
            Writer.writeBytes(responseContent, this.clientSocketOutputData);

            this.clientSocketInputData.close();
            this.clientSocketOutputData.close();
            this.clientSocket.close();
        } catch(IOException io) {
            io.printStackTrace();
        }
    }
}
