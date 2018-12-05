package com.webserver.handlers;

import com.webserver.constants.WebConstants;
import com.webserver.enums.Method;
import com.webserver.parsers.ResponseSerializer;

public final class RequestHandlerFactory {
    private RequestHandlerFactory() {}

    public static RequestHandler createRequestHandler(Method method) {
        ResponseSerializer responseSerializer = new ResponseSerializer();

        if (method == Method.GET) {
            return new GetRequestHandler(responseSerializer);
        }

        return null;
    }
}
