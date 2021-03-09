package com.orderservice.service;

import com.orderservice.client.ProductEndpoint;
import com.orderservice.command.CreateOrderCommand;
import com.orderservice.dto.OrderDto;
import com.orderservice.dto.ProductDto;
import com.orderservice.model.OrderModel;
import com.orderservice.query.GetOrdersQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final ProductEndpoint productEndpoint;

    public void create(OrderDto orderDto) {
        ProductDto productDto = productEndpoint.getById(orderDto.getProductid());
        CreateOrderCommand cmd = new CreateOrderCommand(
                UUID.randomUUID().toString(),
                productDto.getPrice(),
                orderDto.getNumber(),
                orderDto.getProductid()
        );
        commandGateway.send(cmd);
    }

    public CompletableFuture<List<OrderModel>> getAll() {
        return queryGateway.query(new GetOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderModel.class));
    }
}
