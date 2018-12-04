package com.webserver.objects;

import java.util.HashMap;

public class Response implements HttpResponse {
    private HashMap<String, String> headers;
    private int statusCode;
    private byte[] wholeResponseData;

    @Override
    public HashMap<String, String> getHeaders() {
        return null;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public byte[] getContent() {
        return new byte[0];
    }

    @Override
    public byte[] getWholeResponseData() {
        return new byte[0];
    }

    @Override
    public void setStatusCode(int statusCode) {

    }

    @Override
    public void setContent(byte[] content) {

    }

    @Override
    public void addHeader(String header, String value) {

    }
}
