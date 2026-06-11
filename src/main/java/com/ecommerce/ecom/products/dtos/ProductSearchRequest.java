package com.ecommerce.ecom.products.dtos;

import lombok.Builder;

@Builder
public record ProductSearchRequest(
        String keyword,
        Long categoryId,
        Integer page,
        Integer size,
        String sortBy,
        String direction
) {
}
