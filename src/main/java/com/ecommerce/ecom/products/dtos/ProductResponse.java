package com.ecommerce.ecom.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    Long id;

    String name;

    String description;

    BigDecimal price;

    Integer stock;
}
