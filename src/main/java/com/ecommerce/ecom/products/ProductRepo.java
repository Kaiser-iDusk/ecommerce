package com.ecommerce.ecom.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
    Page<Product> findByActiveTrue(Pageable pageable);
    Page<Product> findByActiveTrueAndNameContainingIgnoreCase(Pageable pageable, String name);
    Page<Product> findByActiveTrueAndCategory_Id(Pageable pageable, long id);
}
