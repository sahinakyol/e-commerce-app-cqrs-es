package com.productservice.aggregate;

import com.core.event.StockUpdatedEvent;
import com.productservice.command.CreateProductCommand;
import com.productservice.command.UpdateStockCommand;
import com.productservice.event.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Product {
    @AggregateIdentifier
    private String id;
    private BigDecimal price;
    private Integer stock;
    private String name;
    private String description;

    @CommandHandler
    public Product(CreateProductCommand adProductCommand) {
        apply(new ProductCreatedEvent(
                adProductCommand.getId(),
                adProductCommand.getPrice(),
                adProductCommand.getStock(),
                adProductCommand.getName(),
                adProductCommand.getDescription()
        ));
    }

    @CommandHandler
    public void handle(UpdateStockCommand updateStockCommand) {
        if (this.stock >= updateStockCommand.getNumber()) {
            apply(new StockUpdatedEvent(
                    updateStockCommand.getId(),
                    updateStockCommand.getNumber()
            ));
        } else {
            throw new RuntimeException("Out of Stock!");
        }
    }

    @EventSourcingHandler
    public void on(StockUpdatedEvent evt){
        id = evt.getId();
        stock = stock - evt.getNumber();
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent evt){
        id = evt.getId();
        price = evt.getPrice();
        stock = evt.getStock();
        name = evt.getName();
        description = evt.getDescription();
    }

}
