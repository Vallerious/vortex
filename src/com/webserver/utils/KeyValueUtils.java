package com.webserver.utils;

import java.util.Map;

public final class KeyValueUtils {
    private KeyValueUtils() {}

    public static String keyValueToString(Map<String, Object> keyValuePairs) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : keyValuePairs.entrySet()) {
            sb.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue().toString())
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
