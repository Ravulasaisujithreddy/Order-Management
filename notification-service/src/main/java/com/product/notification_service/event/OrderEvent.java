package com.product.notification_service.event;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private String message;
}
