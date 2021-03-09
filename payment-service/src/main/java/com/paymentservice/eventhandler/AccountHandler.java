package com.paymentservice.eventhandler;

import com.paymentservice.event.PaymentCreatedEvent;
import com.paymentservice.event.WithdrawMoneyEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountHandler {
    private final EventGateway eventGateway;

    @EventHandler
    public void handle(PaymentCreatedEvent event){
        WithdrawMoneyEvent withdrawMoneyEvent = WithdrawMoneyEvent.builder().amount(event.getTotalAmount()).userid(event.getUserid()).build();
        eventGateway.publish(withdrawMoneyEvent);
    }

}
