package com.webserver.objects;

import com.webserver.enums.StatusCodes;

import java.util.HashMap;

public interface HttpResponse {
    HashMap<String, Object> getHeaders();

    StatusCodes getStatusCode();

    byte[] getBody();

    void setStatusCode(StatusCodes statusCode);

    void setBody(byte[] content);

    void addHeader(String header, Object value);

    String toString();
}
