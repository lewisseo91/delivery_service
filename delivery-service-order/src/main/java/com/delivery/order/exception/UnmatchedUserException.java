package com.delivery.order.exception;

public class UnmatchedUserException extends RuntimeException {
    private static final long serialVersionUID = 10005L;

    public UnmatchedUserException() {
    }

    public UnmatchedUserException(String message) {
        super(message);
    }
}
