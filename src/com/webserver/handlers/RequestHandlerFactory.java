package com.webserver.handlers;

import com.webserver.enums.Method;
import com.webserver.objects.Request;
import com.webserver.parsers.ResponseSerializer;
import com.webserver.session.ISession;

import java.util.HashMap;

public final class RequestHandlerFactory {
    private HashMap<String, PostRequestHandler> postRequestHandler;
    private ISession session;

    public RequestHandlerFactory(ISession session) {
        this.postRequestHandler = new HashMap<>();
        this.session = session;
    }

    public RequestHandler createRequestHandler(Request request) {
        ResponseSerializer responseSerializer = new ResponseSerializer();

        if (request.getMethod() == Method.GET) {
            return new GetRequestHandler(responseSerializer, this.session);
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
