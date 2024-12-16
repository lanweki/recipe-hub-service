package com.recipehub.lanweki.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        var message = ex.getMessage() != null ? ex.getMessage() : ErrorCode.NOT_FOUND_EXCEPTION.getMessage();
        var errorResponse = new ErrorResponse(message, ErrorCode.NOT_FOUND_EXCEPTION.getCode());

        logger.error(message);
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        var message = ex.getMessage() != null ? ex.getMessage() : ErrorCode.BAD_REQUEST_EXCEPTION.getMessage();
        var errorResponse = new ErrorResponse(message, ErrorCode.BAD_REQUEST_EXCEPTION.getCode());

        logger.error(message);
        return ResponseEntity.status(400).body(errorResponse);
    }

}
