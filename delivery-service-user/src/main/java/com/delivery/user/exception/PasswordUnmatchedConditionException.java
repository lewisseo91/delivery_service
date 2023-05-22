package com.delivery.user.exception;

public class PasswordUnmatchedConditionException extends RuntimeException {
    private static final long serialVersionUID = 10013L;

    public PasswordUnmatchedConditionException() {
    }

    public PasswordUnmatchedConditionException(String message) {
        super(message);
    }
}
