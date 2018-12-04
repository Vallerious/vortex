package com.webserver.objects;

import java.util.HashMap;

public class Response implements HttpResponse {
    private HashMap<String, String> headers;
    private int statusCode;
    private byte[] wholeResponseData;


    @Override
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
    }

    @Override
    public byte[] getWholeResponseData() {
        return new byte[0];
    }

    @Override
    public void setWholeResponseData(byte[] data) {

    }

    @Override
    public void setStatusCode(int statusCode) {

    }

    @Override
    public void setBody(byte[] content) {

    }

    @Override
    public void addHeader(String header, String value) {

    }
}
