package com.paymentservice.aggregate;

import com.core.event.PaymentCreatedEvent;
import com.paymentservice.command.PaymentCreateCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Payment {
    @AggregateIdentifier
    private String id;

    private String orderId;

    private BigDecimal totalAmount;

    private String userid;

    private String status;

    @CommandHandler
    public Payment(PaymentCreateCommand command) {
        apply(new PaymentCreatedEvent(
                command.getId(),
                command.getOrderId(),
                command.getPrice().multiply(new BigDecimal(command.getNumber())),
                command.getUserid()
        ));
    }

    @EventSourcingHandler
    public void on(PaymentCreatedEvent paymentCreatedEvent) {
        this.id = paymentCreatedEvent.getId();
        this.orderId = paymentCreatedEvent.getOrderId();
        this.userid = paymentCreatedEvent.getUserid();
        this.totalAmount = paymentCreatedEvent.getTotalAmount();
        this.status = "PAID";
    }

}
