package com.product.order_service.event;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private String message;
}
