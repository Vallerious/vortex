package com.webserver.handlers;

import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.ResponseSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class RequestHandler {
    protected ResponseSerializer responseSerializer;

    public RequestHandler(ResponseSerializer responseSerializer) {
        this.responseSerializer = responseSerializer;
    }

    public byte[] handle(Request req) {
        Response res = new Response();

        Date d1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm a");
        res.addHeader("Date", df.format(d1));
        res.addHeader("Server", "Valeri Server 11");
        res.addHeader("Last-Modified", df.format(d1));
        res.addHeader("Connection", "Keep-Alive");

        return handle_internal(req, res);
    }

    protected abstract byte[] handle_internal(Request req, Response res);
}
