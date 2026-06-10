package com.ecommerce.ecom.users;

import com.ecommerce.ecom.users.dtos.LoginRequest;
import com.ecommerce.ecom.users.dtos.RegisterRequest;
import com.ecommerce.ecom.users.dtos.UserMapper;
import com.ecommerce.ecom.users.dtos.UserResponse;
import com.ecommerce.ecom.users.exceptions.PasswordMismatchException;
import com.ecommerce.ecom.users.exceptions.UserAlreadyExistsException;
import com.ecommerce.ecom.users.exceptions.UserDoesNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse registerUser(RegisterRequest registerRequest) {
        if(userRepo.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistsException(registerRequest.getEmail());
        }

        User u = UserMapper.toUser(registerRequest);
        u.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        u = userRepo.save(u);

        return UserMapper.toUserResponse(u);
    }

    public void loginUser(LoginRequest loginRequest){
        User u = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new UserDoesNotExistsException(loginRequest.getEmail())
        );
        if(!passwordEncoder.matches(loginRequest.getPassword(), u.getPassword())){
            throw new PasswordMismatchException(u.getEmail());
        }
    }
}
