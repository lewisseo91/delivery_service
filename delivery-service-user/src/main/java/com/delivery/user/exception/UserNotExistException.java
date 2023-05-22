package com.delivery.user.exception;

public class UserNotExistException extends RuntimeException {
    private static final long serialVersionUID = 10010L;

    public UserNotExistException() {
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
