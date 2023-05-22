package com.delivery.user.exception;

public class UserNotSignedUpException extends RuntimeException {
    private static final long serialVersionUID = 10011L;

    public UserNotSignedUpException() {
    }

    public UserNotSignedUpException(String message) {
        super(message);
    }
}
