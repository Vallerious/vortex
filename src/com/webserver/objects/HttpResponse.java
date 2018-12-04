package com.webserver.objects;

import java.util.HashMap;

public interface HttpResponse {
    HashMap<String, String> getHeaders();

    int getStatusCode();

    byte[] getBody();

    byte[] getWholeResponseData();

    void setWholeResponseData(byte[] data);

    void setStatusCode(int statusCode);

    void setBody(byte[] content);

    void addHeader(String header, String value);
}
