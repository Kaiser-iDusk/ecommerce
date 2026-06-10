package com.ecommerce.ecom.categories.dtos;

import com.ecommerce.ecom.categories.Category;

public class CategoryMapper {
    public static CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Category toCategory(CreateCategoryRequest request){
        return Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }
}
