package com.webserver.handlers;

import com.webserver.constants.WebConstants;
import com.webserver.enums.StatusCodes;
import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.ResponseSerializer;
import com.webserver.utils.ResponsePayloadLoader;
import com.webserver.utils.UrlFileFinder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class GetRequestHandler extends RequestHandler {
    public GetRequestHandler(ResponseSerializer responseSerializer) {
        super(responseSerializer);
    }

    @Override
    protected byte[] handle_internal(Request req, Response res) {
        String path = req.getUrl();

        if (path.isEmpty() || path.equals("/")) {
            path = "/index";
        }

        try {
            UrlFileFinder urlFileFinder = new UrlFileFinder(path);
            ResponsePayloadLoader rpl = new ResponsePayloadLoader(res, urlFileFinder);
            rpl.load();

            res.setStatusCode(StatusCodes.OK);
        } catch (FileNotFoundException | NoSuchFileException fnfe) { // TODO: Separate errors better (400, 500, 404)
            try {
                UrlFileFinder urlFileFinder = new UrlFileFinder("404");
                ResponsePayloadLoader rpl = new ResponsePayloadLoader(res, urlFileFinder);
                rpl.load();
            } catch (IOException e) {
                // ignore this for now
            } finally {
                res.setStatusCode(StatusCodes.NotFound);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return this.responseSerializer.toBytes(res);
    }
}
