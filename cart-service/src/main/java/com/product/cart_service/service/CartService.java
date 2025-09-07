package com.product.cart_service.service;

import com.product.cart_service.entity.CartItem;
import com.product.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.restTemplate = new RestTemplate();
    }

    public void addItem(Long userId, Long productId, int quantity) {
        String url = "http://product-service:8081/products/" + productId;
        CartItem product = restTemplate.getForObject(url, CartItem.class);

        if (product != null) {
            product.setQuantity(quantity);
            cartRepository.addItemToCart(userId, product);
        }
    }

    public List<Object> getCart(Long userId) {
        return cartRepository.getCartItems(userId);
    }

    public void clearCart(Long userId) {
        cartRepository.clearCart(userId);
    }
}
