package com.restaurantevirtual.exception;

public class EmailUniqueViolationException extends RuntimeException {
    public EmailUniqueViolationException(String message) {
        super(message);
    }
}
