package com.product.order_service.controller;

import com.product.order_service.entity.Order;
import com.product.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return orderService.placeOrder(userId, productId, quantity);
    }
}
