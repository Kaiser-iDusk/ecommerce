package com.ecommerce.ecom.cart.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartItemResponse(
        Long itemId,
        Long productId,
        String productName,
        BigDecimal price,
        Integer quantity,
        BigDecimal subtotal
) {
}
