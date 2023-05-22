package com.delivery.order.exception;

public class OrderAlreadyStartedException extends RuntimeException {
    private static final long serialVersionUID = 10006L;

    public OrderAlreadyStartedException() {
    }

    public OrderAlreadyStartedException(String message) {
        super(message);
    }
}
