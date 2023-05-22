package com.delivery.user.exception;

public class PasswordNotExistException extends RuntimeException {
    private static final long serialVersionUID = 10008L;

    public PasswordNotExistException() {
    }

    public PasswordNotExistException(String message) {
        super(message);
    }
}
