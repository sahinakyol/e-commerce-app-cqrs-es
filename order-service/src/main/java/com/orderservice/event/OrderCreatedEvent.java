package com.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderCreatedEvent {
    @TargetAggregateIdentifier
    private String orderId;
    private BigDecimal price;
    private Integer number;
    private String productId;
    private String userid;
}
