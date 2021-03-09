package com.productservice.projection;

import com.productservice.event.ProductCreatedEvent;
import com.productservice.event.StockUpdatedEvent;
import com.productservice.model.ProductModel;
import com.productservice.query.FindProductByIdQuery;
import com.productservice.query.GetProductsQuery;
import com.productservice.repository.ProductProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductProjection {
    private final ProductProjectionRepository productProjectionRepository;

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductModel productModel = new ProductModel(
                productCreatedEvent.getId(),
                productCreatedEvent.getPrice(),
                productCreatedEvent.getStock(),
                productCreatedEvent.getName(),
                productCreatedEvent.getDescription()
        );
        productProjectionRepository.save(productModel);
    }

    @EventHandler
    public void on(StockUpdatedEvent stockUpdatedEvent) {
        ProductModel productSummary = productProjectionRepository.findById(stockUpdatedEvent.getId()).orElse(null);
        productSummary.setStock(productSummary.getStock() - stockUpdatedEvent.getStock());
        productProjectionRepository.save(productSummary);
    }

    @QueryHandler
    public List<ProductModel> handle(GetProductsQuery getProductsQuery) {
        return productProjectionRepository.findAll();
    }

    @QueryHandler
    public Optional<ProductModel> handle(FindProductByIdQuery byIdQuery) {
        return productProjectionRepository.findById(byIdQuery.getId());
    }
}
