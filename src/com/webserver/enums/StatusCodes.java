package com.webserver.enums;

public enum StatusCodes {
    OK(200),
    NotFound(404),
    Forbidden(403),
    Unauthorized(401),
    PermanentRedirect(308),
    Found(302);

    private int statusCode;

    StatusCodes(int code) {
        this.statusCode = code;
    }

    public int asStatusNumber() {
        return this.statusCode;
    }
}
