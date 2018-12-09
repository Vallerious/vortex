package com.webserver.parsers;

import com.webserver.enums.Method;
import com.webserver.objects.Request;
import com.webserver.utils.KeyValueUtils;

import java.util.List;
import java.util.Map;

public class RequestParser implements IRequestParser {
    private static final String FIRST_LINE_REGEX = "\\s+";

    public RequestParser() {
        // TODO take header parser
    }

    @Override
    public Request parse(List<String> data) {
        if (data.isEmpty()) {
            return null;
        }

        Request request = new Request();

        // Parse first line - example: GET /hello.htm HTTP/1.1
        String[] firstLineReqParams = data.get(0).split(FIRST_LINE_REGEX);
        Method reqMethod = Method.valueOf(firstLineReqParams[0].toUpperCase());
        String url = firstLineReqParams[1];

        request.setMethod(reqMethod);
        request.setUrl(url);

        // Parse Headers
        int lineIdx = 1;

        while (lineIdx <= data.size() - 1 && !data.get(lineIdx).isEmpty()) {
            Map.Entry<String, Object> header = KeyValueUtils.parseStringToEntry(data.get(lineIdx));

            request.getHeaders().put(header.getKey(), header.getValue().toString());

            lineIdx++;
        }

        // do we have more after the headers CRLF? payload? Hmm for now we will support only application/x-www-form-urlencoded
        if (lineIdx < data.size() - 1) {
            lineIdx++; // move after this CRLF

            while (lineIdx <= data.size() - 1 && !data.get(lineIdx).isEmpty()) {
                Map<String, String> formData = KeyValueUtils.parseQuerylikeParams(data.get(lineIdx));
                request.setFormData(formData);
                lineIdx++;
            }
        }

        return request;
    }
}
