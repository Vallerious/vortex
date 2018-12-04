package com.webserver.handlers;

import com.webserver.objects.Request;
import com.webserver.parsers.HttpRequestParser;

public class RequestHandler {
    private HttpRequestParser requestParser;

    public RequestHandler(HttpRequestParser requestParser) {
        this.requestParser = requestParser;
    }

    public byte[] handle(String reqContent) {
        Request req = this.requestParser.parse(reqContent);
    }
}
