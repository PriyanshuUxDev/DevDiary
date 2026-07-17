package com.Spring.DevDiary.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<String> handleTimeout(SocketTimeoutException e) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("External AI service timed out. Please try again shortly.");
    }
}