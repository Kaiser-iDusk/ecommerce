package com.ecommerce.ecom.cart;

import com.ecommerce.ecom.cart.dtos.AddToCartRequest;
import com.ecommerce.ecom.cart.dtos.CartItemResponse;
import com.ecommerce.ecom.cart.dtos.CartResponse;
import com.ecommerce.ecom.cart.dtos.UpdateCartItemRequest;
import com.ecommerce.ecom.cart.exceptions.CartItemNotFoundException;
import com.ecommerce.ecom.cart.exceptions.CartNotFoundException;
import com.ecommerce.ecom.common.Utils;
import com.ecommerce.ecom.products.Product;
import com.ecommerce.ecom.products.ProductRepo;
import com.ecommerce.ecom.products.exceptions.ProductNotFoundException;
import com.ecommerce.ecom.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    private final ProductRepo productRepo;

    private Cart getCurrentUserCart(){
        User user = Utils.getCurrentUser();
        if(user == null){
            throw new RuntimeException("Please authenticate first.");
        }
        return cartRepo.findByUser_Id(user.getId()).orElseThrow(
                ()->new CartNotFoundException("Cart not found.")
        );
    }

    private CartResponse buildResponse(Cart cart){
        List<CartItem> cartItems = cartItemRepo.findByCart_Id(cart.getId());
        List<CartItemResponse> items = cartItems.stream().map(
                item -> {
                    BigDecimal subTotal = item.getProduct()
                            .getPrice().multiply(
                                 BigDecimal.valueOf(item.getQuantity())
                            );
                    return CartItemResponse.builder()
                            .itemId(item.getId())
                            .productId(item.getProduct().getId())
                            .productName(item.getProduct().getName())
                            .price(item.getProduct().getPrice())
                            .quantity(item.getQuantity())
                            .subtotal(subTotal)
                            .build();
                }
        ).toList();
        BigDecimal total = items.stream().map(
                CartItemResponse::subtotal
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
        return CartResponse.builder().items(items).cartId(cart.getId()).totalPrice(total).build();
    }

    private CartItem getCartItemOwnedByCurrentUser(Long itemId, Cart cart){
        CartItem item = cartItemRepo.findById(itemId).orElseThrow(
                () -> new CartItemNotFoundException("Cart Item not found.")
        );
        if(!item.getCart().getId().equals(cart.getId())){
            throw new AccessDeniedException("Cart item not owned by user.");
        }
        return item;
    }

    public CartResponse getCart(){
        Cart currentCart = getCurrentUserCart();
        return buildResponse(currentCart);
    }

    @Transactional
    public CartResponse addItem(AddToCartRequest request){
        Cart cart = getCurrentUserCart();
        Product product = productRepo.findById(request.productId()).orElseThrow(
                () -> new ProductNotFoundException(request.productId())
        );

        CartItem cartItem = cartItemRepo.findByCart_IdAndProduct_Id(
                cart.getId(), product.getId()
        ).orElse(null);

        if(cartItem == null){
            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.quantity()).build();
        }
        else{
            cartItem.setQuantity(
                    cartItem.getQuantity() + request.quantity()
            );
        }
        cartItemRepo.save(cartItem);
        return buildResponse(cart);
    }

    @Transactional
    public CartResponse updateQuantity(
            Long itemId,
            UpdateCartItemRequest request
    ){
        Cart cart = getCurrentUserCart();
        CartItem cartItem = getCartItemOwnedByCurrentUser(itemId, cart);
        cartItem.setQuantity(request.quantity());
        if(cartItem.getQuantity() == 0){
            cartItemRepo.delete(cartItem);
        }
        else {
            cartItemRepo.save(cartItem);
        }
        return buildResponse(cart);
    }

    @Transactional
    public void clearCart(){
        Cart cart = getCurrentUserCart();
        List<CartItem> cartItems = cartItemRepo.findByCart_Id(cart.getId());
        cartItemRepo.deleteAll(cartItems);
    }

    @Transactional
    public void removeItem(Long itemId){
        Cart cart = getCurrentUserCart();
        CartItem cartItem = getCartItemOwnedByCurrentUser(itemId, cart);
        cartItemRepo.delete(cartItem);
    }
}
