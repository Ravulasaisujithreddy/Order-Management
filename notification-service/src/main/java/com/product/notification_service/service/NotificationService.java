package com.product.notification_service.service;

import com.product.notification_service.event.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
        // In real-world applications, send email/SMS notifications here
    }
}
