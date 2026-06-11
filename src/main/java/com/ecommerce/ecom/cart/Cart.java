package com.ecommerce.ecom.cart;

import com.ecommerce.ecom.common.BaseEntity;
import com.ecommerce.ecom.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "carts")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
