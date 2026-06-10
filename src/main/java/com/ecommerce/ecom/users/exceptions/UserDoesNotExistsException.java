package com.ecommerce.ecom.users.exceptions;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String email) {
        super("User does not exist with email: " + email);
    }
}
