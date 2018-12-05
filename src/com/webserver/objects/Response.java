package com.webserver.objects;

import com.webserver.constants.WebConstants;
import com.webserver.enums.StatusCodes;
import com.webserver.utils.KeyValueUtils;

import java.util.HashMap;

public class Response implements HttpResponse {
    private HashMap<String, Object> headers;
    private StatusCodes statusCode;
    private byte[] body;

    public Response() {
        this.headers = new HashMap<>();
    }

    @Override
    public HashMap<String, Object> getHeaders() {
        return this.headers;
    }

    @Override
    public StatusCodes getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getBody() {
        return this.body;
    }

    @Override
    public void setStatusCode(StatusCodes statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setBody(byte[] content) {
        this.body = content;
    }

    @Override
    public void addHeader(String header, Object value) {
        this.headers.put(header, value.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(WebConstants.HTTP_VERSION)
                .append(" ")
                .append(this.getStatusCode().asStatusNumber())
                .append(" ")
                .append(this.getStatusCode().toString())
                .append("\r\n")
                .append(KeyValueUtils.keyValueToString(this.getHeaders()))
                .append("\r\n");

        return sb.toString();
    }
}
