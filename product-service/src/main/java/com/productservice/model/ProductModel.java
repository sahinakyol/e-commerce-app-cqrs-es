package com.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "PRODUCT_MODEL")
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    private String id;
    private BigDecimal price;
    private Integer stock;
    private String name;
    private String description;
}

