package com.ecommerce.ecom.products.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank
        String name,

        @Size(max=2000)
        String description,

        @NotNull
        @PositiveOrZero
        BigDecimal price,

        @NotNull
        @PositiveOrZero
        Integer stock,

        String imgUrl,

        @Positive
        Long categoryId
) {
}
