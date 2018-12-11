package com.webserver.utils;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public final class KeyValueUtils {
    private static final String SEPARATOR = ":\\s+";
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

    /**
     *
     * @param keyValueStr String (key: value)
     * @return
     */
    public static Map.Entry<String, Object> parseStringToEntry(String keyValueStr, String separator) {
        String[] headerKeyVal = keyValueStr.split(separator);
        String key = null;
        String val = null;

        if (headerKeyVal.length > 0) {
            key = headerKeyVal[0].trim();
        }

        if (headerKeyVal.length > 1) {
            val = headerKeyVal[1].trim();
        }

        return new AbstractMap.SimpleEntry<>(key, val);
    }

    public static Map.Entry<String, Object> parseStringToEntry(String keyValueStr) {
        return parseStringToEntry(keyValueStr, SEPARATOR);
    }

    public static Map<String, String> parseQuerylikeParams(String queryStr) {
        Map<String, String> queryParams = new HashMap<>();
        String[] keyValuePairs = queryStr.split("&");

        for (String keyValuePair : keyValuePairs) {
            Map.Entry<String, Object> entry = parseStringToEntry(keyValuePair, "=");
            queryParams.put(entry.getKey(), entry.getValue() == null ? null : entry.getValue().toString());
        }

        return queryParams;
    }
}
