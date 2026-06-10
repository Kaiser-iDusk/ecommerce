package com.ecommerce.ecom.security;

public record ErrorResponse(
        int status,
        String message
) {
}
