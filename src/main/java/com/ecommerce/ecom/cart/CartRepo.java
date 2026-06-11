package com.ecommerce.ecom.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUser_Id(UUID user_id);
    Optional<Cart> findByUser_Email(String email);
}
