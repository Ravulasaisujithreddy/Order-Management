package com.product.order_service.service;

import com.product.order_service.entity.Order;
import com.product.order_service.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void processOrder(String message) {
        System.out.println("Received Order Event: " + message);
        // Here, process order details and communicate with Inventory Service
    }

    public Order placeOrder(Long userId, Long productId, int quantity) {
        Order order = new Order(null, userId, productId, quantity, "PENDING");
        return orderRepository.save(order);
    }
}
