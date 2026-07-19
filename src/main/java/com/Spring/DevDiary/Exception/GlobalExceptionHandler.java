package com.Spring.DevDiary.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<String> handleTimeout(SocketTimeoutException e) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("External AI service timed out. Please try again shortly.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception e) {
        log.error("Unhandled exception: ", e); // full stack trace in console
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
    }
}