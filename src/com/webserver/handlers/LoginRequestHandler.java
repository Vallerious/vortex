package com.webserver.handlers;

import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.ResponseSerializer;

public class LoginRequestHandler extends PostRequestHandler {
    public LoginRequestHandler(ResponseSerializer responseSerializer) {
        super("/login", responseSerializer);
    }

    @Override
    protected byte[] handle_internal(Request req, Response res) {
        return new byte[0];
    }
}
