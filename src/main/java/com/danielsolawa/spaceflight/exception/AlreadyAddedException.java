package com.danielsolawa.spaceflight.exception;

public class AlreadyAddedException extends RuntimeException {

    public AlreadyAddedException() {
    }

    public AlreadyAddedException(String message) {
        super(message);
    }

    public AlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyAddedException(Throwable cause) {
        super(cause);
    }

    public AlreadyAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
