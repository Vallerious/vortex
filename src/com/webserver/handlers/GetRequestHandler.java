package com.webserver.handlers;

import com.webserver.enums.StatusCodes;
import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.HttpRequestParser;
import com.webserver.parsers.ResponseSerializer;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetRequestHandler extends RequestHandler {
    public GetRequestHandler(ResponseSerializer responseSerializer) {
        super(responseSerializer);
    }

    @Override
    protected byte[] handle_internal(Request req, Response res) {
        String path = req.getUrl();
        boolean isAsset = path.contains(".");
        Path filePath;

        if (!isAsset) {
            filePath = Paths.get("src/resources/pages", path + ".html");
        } else {
            filePath = Paths.get("src/resources/assets", path);
        }

        File asset = new File(filePath.toString());
        String mimeType = null;

        try {
            res.setBody(Files.readAllBytes(asset.toPath()));
            mimeType = Files.probeContentType(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isAsset) {
            res.addHeader("Content-Length", res.getBody().length);
            if (mimeType != null) {
                res.addHeader("Content-Type", mimeType);
            }
        } else {
            res.addHeader("Content-Length", new String(res.getBody()).length());
            res.addHeader("Content-Type", "text/html");
        }

        res.setStatusCode(StatusCodes.OK);

        return this.responseSerializer.toBytes(res);
    }
}
