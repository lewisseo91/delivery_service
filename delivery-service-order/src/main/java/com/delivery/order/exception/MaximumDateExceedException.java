package com.delivery.order.exception;

public class MaximumDateExceedException extends RuntimeException {
    private static final long serialVersionUID = 10004L;

    public MaximumDateExceedException() {
    }

    public MaximumDateExceedException(String message) {
        super(message);
    }
}
