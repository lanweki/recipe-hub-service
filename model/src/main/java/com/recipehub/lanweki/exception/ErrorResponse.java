package com.recipehub.lanweki.exception;

public record ErrorResponse(
        String message,
        int errorCode) {
}
