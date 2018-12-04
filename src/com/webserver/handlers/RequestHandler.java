package com.webserver.handlers;

import com.webserver.enums.Method;
import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.HttpRequestParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler {
    private HttpRequestParser requestParser;

    public RequestHandler(HttpRequestParser requestParser) {
        this.requestParser = requestParser;
    }

    public byte[] handle(String reqContent) {
        Request req = this.requestParser.parse(reqContent);
        Response res = new Response();

        if (req.getMethod() == Method.GET) {
            String path = req.getUrl();
            Path filePath;

            if (path.contains(".html") || path.contains(".htm")) {
                filePath = Paths.get("src/resources/pages", path);
            } else if (!path.contains(".")) {
                filePath = Paths.get("src/resources/pages", path + ".html");
            } else {
                filePath = Paths.get("src/resources/assets", path);
            }

            File asset = new File(filePath.toString());
            try {
                res.setWholeResponseData(Files.readAllBytes(asset.toPath()));
            } catch (IOException e) {
                // set different response
            }

            
        }

        return new byte[1];
    }
}
