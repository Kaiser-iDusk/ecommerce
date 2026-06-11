package com.ecommerce.ecom.cart.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record AddToCartRequest(
        @NotNull Long productId,
        @Positive Integer quantity
) {
}
