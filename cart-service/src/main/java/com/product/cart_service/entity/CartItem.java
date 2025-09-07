package com.product.cart_service.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@RedisHash("cartItems")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CartItem implements Serializable {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}
