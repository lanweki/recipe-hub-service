package com.recipehub.lanweki.exception;

public enum ErrorCode {
    NOT_FOUND_EXCEPTION(10001, "Requested object does not exist"),
    BAD_REQUEST_EXCEPTION(10002, "Request has invalid data");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
