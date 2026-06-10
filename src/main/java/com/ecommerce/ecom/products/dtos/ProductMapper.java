package com.ecommerce.ecom.products.dtos;

import com.ecommerce.ecom.products.Product;

public class ProductMapper {
    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .imgUrl(product.getImgUrl())
                .active(product.getActive())
                .build();
    }

    public static Product toProduct(CreateProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .stock(productRequest.stock())
                .imgUrl((productRequest.imgUrl() == null) ? "Default Image URL" : productRequest.imgUrl())
                .active(true)
                .build();
    }
}
