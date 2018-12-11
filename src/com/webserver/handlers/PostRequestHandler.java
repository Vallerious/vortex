package com.webserver.handlers;

import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.ResponseSerializer;
import com.webserver.session.ISession;

public abstract class PostRequestHandler extends RequestHandler {
    private String route;


    public PostRequestHandler(String route, ResponseSerializer responseSerializer, ISession session) {
        super(responseSerializer, session);
        this.route = route;
    }

    public PostRequestHandler(String route, ResponseSerializer responseSerializer) {
        super(responseSerializer);
        this.route = route;
    }

    @Override
    protected abstract byte[] handle_internal(Request req, Response res);

    public String getRoute() {
        return this.route;
    }
}
