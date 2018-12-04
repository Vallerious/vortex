package com.webserver.parsers;

import com.webserver.enums.Method;
import com.webserver.objects.Request;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class RequestParser implements IRequestParser {
    private static final String FIRST_LINE_REGEX = "\\s+";

    public RequestParser() {
        // TODO take header parser
    }

    @Override
    public Request parse(List<String> data) {
        String[] firstLineReqParams = data.get(0).split(FIRST_LINE_REGEX);
        Method reqMethod = Method.valueOf(firstLineReqParams[0].toUpperCase());
        String url = firstLineReqParams[1];
        HashMap<String, String> headers = this.parseHeaders(data);
        String body = ""; // TODO: parse the body

        return new Request(reqMethod, url, headers, body);
    }

    private HashMap<String, String> parseHeaders(List<String> data) {
        HashMap<String, String> headers = new HashMap<>();

        for (int idx = 1, len = data.size(); idx < len; idx++) {
            String line = data.get(idx);

            if (line.isEmpty()) break;

            String[] headerKeyVal = line.split(":\\s+");
            String key = headerKeyVal[0].trim();
            String val = headerKeyVal[1].trim();

            if (!key.isEmpty() && !val.isEmpty()) {
                headers.put(key, val);
            }
        }

        return headers;
    }
}
