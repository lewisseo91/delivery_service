package com.delivery.user.exception;

public class AuthRoleParseException extends RuntimeException {
    private static final long serialVersionUID = 10014L;

    public AuthRoleParseException() {
    }

    public AuthRoleParseException(String message) {
        super(message);
    }
}
