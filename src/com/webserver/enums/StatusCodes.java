package com.webserver.enums;

public enum StatusCodes {
    OK(200),
    NotFound(404);

    private int statusCode;

    StatusCodes(int code) {
        this.statusCode = code;
    }

    public int asStatusNumber() {
        return this.statusCode;
    }
}
