package com.stockgro.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> onBaseException(BaseException e) {
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

}
