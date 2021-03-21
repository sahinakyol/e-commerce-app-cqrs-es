package com.shipmentservice.saga;

import com.core.event.PaymentCreatedEvent;
import com.shipmentservice.command.CreateShipmentCommand;
import com.shipmentservice.command.ShipmentDeliverCommand;
import com.shipmentservice.event.CreateShipmentEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
public class ShipmentSaga {

    private transient CommandGateway commandGateway;
    private String paymentid;
    private String productId;
    private Integer productCount;
    private String address;
    private String cargoFirm;

    @Autowired
    public ShipmentSaga(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "paymentid")
    public void on(PaymentCreatedEvent event) {
        this.paymentid = event.getPaymentid();
        this.productId = event.getProductId();
        this.productCount = event.getNumber();
        this.address = "ADRESS";
        this.cargoFirm = "TNT";

        SagaLifecycle.associateWith("productId", productId);

        commandGateway.send(new CreateShipmentCommand(paymentid, productId, productCount, address, cargoFirm))
                .exceptionally(throwable -> {
                    /*commandGateway.send(new RejectShipmentCommand());*/
                    return null;
                });

    }

    @EndSaga
    @SagaEventHandler(associationProperty = "paymentid")
    public void on(CreateShipmentEvent event) {
        if (!paymentid.equals(event.getPaymentid())) {
            return;
        }

        commandGateway.send(new ShipmentDeliverCommand(paymentid, UUID.randomUUID().toString()));

        SagaLifecycle.end();
    }
}
