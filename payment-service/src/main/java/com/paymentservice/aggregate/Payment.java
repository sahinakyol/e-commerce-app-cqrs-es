package com.paymentservice.aggregate;

import com.paymentservice.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

@Aggregate
@RequiredArgsConstructor
public class Payment {
    @AggregateIdentifier
    private String id;

    private String orderId;

    private BigDecimal totalAmount;

    private String userid;

    private String status;

    @EventSourcingHandler
    protected void on(PaymentCreatedEvent paymentCreatedEvent) {
        this.id = UUID.randomUUID().toString();
        this.orderId = paymentCreatedEvent.getOrderId();
        this.userid = paymentCreatedEvent.getUserid();
        this.totalAmount = paymentCreatedEvent.getTotalAmount();
        this.status = "PAID";
    }

}
