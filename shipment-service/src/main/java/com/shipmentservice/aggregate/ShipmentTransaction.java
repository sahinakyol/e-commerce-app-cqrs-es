package com.shipmentservice.aggregate;

import com.shipmentservice.command.ShipmentDeliverCommand;
import com.shipmentservice.event.ShipmentDeliverEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.EntityId;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@RequiredArgsConstructor
public class ShipmentTransaction {

    @EntityId
    private final String transactionId;
    private boolean delivered = false;

    @CommandHandler
    public void handle(ShipmentDeliverCommand command) {
        if (delivered) {
            throw new IllegalStateException("Transaction has already done!");
        }
        apply(new ShipmentDeliverEvent(transactionId));
    }

    @EventSourcingHandler
    public void on(ShipmentDeliverEvent event) {
        if (!transactionId.equals(event.getTransactionId())) {
            return;
        }

        this.delivered = true;
    }
}
