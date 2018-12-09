package com.webserver.utils;

import com.webserver.constants.WebConstants;
import com.webserver.objects.Response;

public class ResponsePayloadLoader {
    private Response response;
    private UrlFileFinder urlFileFinder;

    public ResponsePayloadLoader(Response response, UrlFileFinder urlFileFinder) {
        this.response = response;
        this.urlFileFinder = urlFileFinder;
    }

    public void load() {
        response.setBody(urlFileFinder.getBytes());

        response.addHeader(WebConstants.CONTENT_LENGTH_HEADER, urlFileFinder.getPayloadLength());
        response.addHeader(WebConstants.CONTENT_TYPE_HEADER, urlFileFinder.getMimeType());
    }
}
