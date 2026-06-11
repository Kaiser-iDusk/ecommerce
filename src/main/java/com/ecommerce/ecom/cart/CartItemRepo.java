package com.ecommerce.ecom.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_Id(UUID cartId);

    Optional<CartItem> findByCart_IdAndProduct_Id(UUID cartId, Long productId);
}
