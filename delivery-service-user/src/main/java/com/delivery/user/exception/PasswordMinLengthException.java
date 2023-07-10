package com.delivery.user.exception;

public class PasswordMinLengthException extends RuntimeException {
    private static final long serialVersionUID = 10009L;

    public PasswordMinLengthException() {
    }

    public PasswordMinLengthException(String message) {
        super(message);
    }
}
