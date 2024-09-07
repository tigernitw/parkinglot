package com.stockgro.backend.exception;

public class BaseException extends RuntimeException {

    private int code;
    private String message;

    public BaseException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
