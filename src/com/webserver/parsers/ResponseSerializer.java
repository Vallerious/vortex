package com.webserver.parsers;

import com.webserver.objects.Response;

public final class ResponseSerializer {

    public byte[] toBytes(Response res) {
        String resText = res.toString();
        byte[] firstBytes = resText.getBytes();
        byte[] responseAsBytes = new byte[firstBytes.length + res.getBody().length];

        System.arraycopy(firstBytes, 0, responseAsBytes, 0, firstBytes.length);
        System.arraycopy(res.getBody(), 0, responseAsBytes, firstBytes.length, res.getBody().length);

        return responseAsBytes;
    }

}
