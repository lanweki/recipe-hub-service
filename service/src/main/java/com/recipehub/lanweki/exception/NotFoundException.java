package com.recipehub.lanweki.exception;

public class NotFoundException extends RuntimeException {

    private static final String NOT_FOUND_MESSAGE = "Element was not found with id=%s";

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Integer id) {
        super(String.format(NOT_FOUND_MESSAGE, id));
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
