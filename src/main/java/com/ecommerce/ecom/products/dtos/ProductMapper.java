package com.ecommerce.ecom.products.dtos;

import com.ecommerce.ecom.common.PagedResponse;
import com.ecommerce.ecom.products.Product;
import org.springframework.data.domain.Page;

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

    public static <T> PagedResponse<T> toPagedRespone(Page<T> page){
        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
