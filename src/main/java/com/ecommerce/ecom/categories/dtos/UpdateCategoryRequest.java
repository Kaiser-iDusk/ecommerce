package com.ecommerce.ecom.categories.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(
        @NotBlank Long id,
        String name,
        String description
) {
}
