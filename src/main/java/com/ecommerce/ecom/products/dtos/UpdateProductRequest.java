package com.ecommerce.ecom.products.dtos;

import java.math.BigDecimal;

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
