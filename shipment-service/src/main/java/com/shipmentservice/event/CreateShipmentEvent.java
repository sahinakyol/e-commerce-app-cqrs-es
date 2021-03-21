package com.shipmentservice.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateShipmentEvent {
    private final String paymentid;
    private final String productId;
    private final Integer productCount;
    private final String address;
    private final String cargoFirm;
}
