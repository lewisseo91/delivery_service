package com.delivery.order.exception;

public class OrderNotExistException extends RuntimeException {
    private static final long serialVersionUID = 10008L;

    public OrderNotExistException() {
    }

    public OrderNotExistException(String message) {
        super(message);
    }
}
