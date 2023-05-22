package com.delivery.user.exception;

public class PasswordNotMatchedException extends RuntimeException {
    private static final long serialVersionUID = 10012L;

    public PasswordNotMatchedException() {
    }

    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
