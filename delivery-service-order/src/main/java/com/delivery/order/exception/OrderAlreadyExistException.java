package com.delivery.order.exception;

public class OrderAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 10007L;

    public OrderAlreadyExistException() {
    }

    public OrderAlreadyExistException(String message) {
        super(message);
    }
}
