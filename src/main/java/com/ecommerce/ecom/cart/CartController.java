package com.ecommerce.ecom.cart;

import com.ecommerce.ecom.cart.dtos.AddToCartRequest;
import com.ecommerce.ecom.cart.dtos.CartResponse;
import com.ecommerce.ecom.cart.dtos.UpdateCartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getCart(){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }

    @PostMapping("/items")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CartResponse> addItem(
            @Validated @RequestBody AddToCartRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addItem(request));
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CartResponse> updateItem(
            @Validated @RequestBody UpdateCartItemRequest request,
            @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartService.updateQuantity(id, request));
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteItem(
            @PathVariable Long id
    ){
        cartService.removeItem(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteCart(){
        cartService.clearCart();
        return ResponseEntity.ok().build();
    }
}
