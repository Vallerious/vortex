package com.webserver.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class Writer {
    private Writer() {}

    public static void writeBytes(byte[] byteData, OutputStream outputStream) throws IOException {
        DataOutputStream primitiveWriter = new DataOutputStream(outputStream);

        primitiveWriter.write(byteData);
    }
}
