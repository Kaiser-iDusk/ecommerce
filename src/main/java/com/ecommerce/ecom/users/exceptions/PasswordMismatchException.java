package com.ecommerce.ecom.users.exceptions;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String email) {
        super("Incorrect password for user with email: " + email);
    }
}
