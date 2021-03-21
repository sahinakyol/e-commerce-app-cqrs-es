package com.shipmentservice.command;

import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
public class ShipmentDeliverCommand {

    @TargetAggregateIdentifier
    private final String paymentid;
    private final String transactionId;
}
