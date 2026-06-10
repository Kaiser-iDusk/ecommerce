package com.ecommerce.ecom.categories.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateCategoryRequest(
        @NotBlank String name,
        String description
) {
}
