package com.ecommerce.ecom.products;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long> {
}
