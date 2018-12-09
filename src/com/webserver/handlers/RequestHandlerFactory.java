package com.webserver.handlers;

import com.webserver.constants.WebConstants;
import com.webserver.enums.Method;
import com.webserver.objects.Request;
import com.webserver.parsers.ResponseSerializer;

import java.util.HashMap;

public final class RequestHandlerFactory {
    private HashMap<String, PostRequestHandler> postRequestHandler;

    public RequestHandlerFactory() {
        this.postRequestHandler = new HashMap<>();
    }

    public RequestHandler createRequestHandler(Request request) {
        ResponseSerializer responseSerializer = new ResponseSerializer();

        if (request.getMethod() == Method.GET) {
            return new GetRequestHandler(responseSerializer);
        } else if (request.getMethod() == Method.POST && !this.postRequestHandler.isEmpty()) {
            RequestHandler handler = null;

            if (this.postRequestHandler.containsKey(request.getUrl())) {
                handler = this.postRequestHandler.get(request.getUrl());
            }

            return handler;
        }

        return null;
    }

    public void registerHandler(String route, PostRequestHandler handler) {
        if (!route.isEmpty() && handler != null) {
            this.postRequestHandler.put(route, handler);
        }
    }
}
