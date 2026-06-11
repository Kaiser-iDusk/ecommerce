package com.ecommerce.ecom.cart.dtos;

import jakarta.validation.constraints.PositiveOrZero;

public record UpdateCartItemRequest(
        @PositiveOrZero Integer quantity
) {
}
