package com.ecommerce.ecom.users.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("User exists with email: " + email);
    }
}
