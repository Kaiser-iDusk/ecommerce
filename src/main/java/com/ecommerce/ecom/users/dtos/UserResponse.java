package com.ecommerce.ecom.users.dtos;

import com.ecommerce.ecom.users.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    UUID id;

    String name;

    String email;

    Role role;
}
