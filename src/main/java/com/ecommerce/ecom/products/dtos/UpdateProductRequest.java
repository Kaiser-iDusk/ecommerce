package com.ecommerce.ecom.products.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String imgUrl,
        Boolean active,
        Long categoryId
) {
}
