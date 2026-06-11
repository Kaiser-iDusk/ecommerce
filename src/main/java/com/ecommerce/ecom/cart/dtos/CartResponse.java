package com.ecommerce.ecom.cart.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record CartResponse(
        UUID cartId,
        List<CartItemResponse> items,
        BigDecimal totalPrice
) {
}
