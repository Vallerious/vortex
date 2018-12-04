package com.webserver.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Reader {
    private Reader() {}

    public static String readAllLines(InputStream inputStream) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder text = new StringBuilder();

        while (buffer.ready()) {
            text.append((char) buffer.read());
        }

        return text.toString();
    }
}
