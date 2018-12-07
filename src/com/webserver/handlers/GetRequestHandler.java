package com.webserver.handlers;

import com.webserver.constants.WebConstants;
import com.webserver.enums.StatusCodes;
import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.HttpRequestParser;
import com.webserver.parsers.ResponseSerializer;
import com.webserver.utils.UrlFileFinder;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetRequestHandler extends RequestHandler {
    public GetRequestHandler(ResponseSerializer responseSerializer) {
        super(responseSerializer);
    }

    @Override
    protected byte[] handle_internal(Request req, Response res) {
        String path = req.getUrl();

        try {
            UrlFileFinder urlFileFinder = new UrlFileFinder(path);
            res.setBody(urlFileFinder.getBytes());

            res.addHeader(WebConstants.CONTENT_LENGTH_HEADER, urlFileFinder.getPayloadLength());
            res.addHeader(WebConstants.CONTENT_TYPE_HEADER, urlFileFinder.getMimeType());

            res.setStatusCode(StatusCodes.OK);
        } catch (IOException e) { // TODO: Separate errors better (400, 500, 404)
            res.setStatusCode(StatusCodes.NotFound);
        }

        return this.responseSerializer.toBytes(res);
    }
}
