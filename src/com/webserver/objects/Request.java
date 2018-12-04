package com.webserver.objects;

import com.webserver.enums.Method;

import java.util.HashMap;

public class Request {
    private Method method;
    private String url;
    private HashMap<String, String> headers;
    private String body;

    public Request(Method method, String url, HashMap<String, String> headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeader(String key) {
        return this.getHeaders().get(key);
    }
}