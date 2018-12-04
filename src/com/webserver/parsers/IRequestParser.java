package com.webserver.parsers;

import com.webserver.objects.Request;

import java.net.Socket;
import java.util.List;
import java.util.stream.Stream;

public interface IRequestParser {
    Request parse(List<String> data);
}
