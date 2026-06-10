package com.ecommerce.ecom.users.dtos;

import com.ecommerce.ecom.users.User;
import com.ecommerce.ecom.users.enums.Role;

public class UserMapper {

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static User toUser(RegisterRequest registerRequest) {
        return User.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .role(Role.CUSTOMER)
                .build();
    }

//    public static User toUser(LoginRequest loginRequest) {
//        return User.builder()
//                .email(registerRequest.getEmail())
//                .name(registerRequest.getName())
//                .role(Role.CUSTOMER)
//                .build();
//    }
}
