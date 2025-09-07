package com.product.notification_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @GetMapping("/test")
    public String testNotification() {
        return "Notification Service is running!";
    }
}
