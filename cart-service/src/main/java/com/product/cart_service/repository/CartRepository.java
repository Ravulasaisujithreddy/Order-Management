package com.product.cart_service.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.product.cart_service.entity.CartItem;

import java.util.List;

@Repository
public class CartRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public CartRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String getCartKey(Long userId) {
        return "cart:" + userId;
    }

    public void addItemToCart(Long userId, CartItem item) {
        redisTemplate.opsForList().rightPush(getCartKey(userId), item);
    }

    public List<Object> getCartItems(Long userId) {
        return redisTemplate.opsForList().range(getCartKey(userId), 0, -1);
    }

    public void clearCart(Long userId) {
        redisTemplate.delete(getCartKey(userId));
    }
}
