package com.ecommerce.ecom.users;
import com.ecommerce.ecom.users.dtos.LoginRequest;
import com.ecommerce.ecom.users.dtos.RegisterRequest;
import com.ecommerce.ecom.users.dtos.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(
            @Validated @RequestBody RegisterRequest registerRequest
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.registerUser(registerRequest)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @Validated @RequestBody LoginRequest loginRequest
    ){
        userService.loginUser(loginRequest);
        return ResponseEntity.ok("Login successful");
    }
}
