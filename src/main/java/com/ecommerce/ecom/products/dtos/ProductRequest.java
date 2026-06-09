package com.ecommerce.ecom.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest{
    @NotBlank
    String name = "No name";

    @NotNull
    String description = "Description not available";

    @PositiveOrZero
    BigDecimal price = BigDecimal.ZERO;

    @PositiveOrZero
    Integer stock = 0;
}
