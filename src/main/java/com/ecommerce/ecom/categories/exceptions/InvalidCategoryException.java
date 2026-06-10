package com.ecommerce.ecom.categories.exceptions;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(Long id) {
        super("Category does not exist with id: "+id);
    }

    public InvalidCategoryException(String name) {
        super("Category does not exist with name: "+name);
    }
}
