package com.webserver.parsers;

import com.webserver.objects.Request;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HttpRequestParser {
    private RequestParser requestParser;

    public HttpRequestParser() {
        this.requestParser = new RequestParser();
    }

    public Request parse(String reqData) {
        return this.requestParser.parse(Arrays.stream(reqData.split(System.lineSeparator())).collect(Collectors.toList()));
    }
}
