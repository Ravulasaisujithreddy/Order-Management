package com.product.cart_service.controller;

import com.product.cart_service.entity.CartItem;
import com.product.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public String addItemToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addItem(userId, productId, quantity);
        return "Product added to cart!";
    }

    @GetMapping("/{userId}")
    public List<Object> getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/{userId}")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared!";
    }
}
