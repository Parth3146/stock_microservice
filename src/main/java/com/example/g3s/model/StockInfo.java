package com.example.g3s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {

    @Column(name = "STOCK_SUPPLIER")
    private String supplier;

    @Column(name = "PRODUCT_ID")
    @NotNull(message = "Enter associated product Id")
    private String productId;

    @Column(name = "AVAILABLE_QUANTITY")
    @Min(0)
    private Long quantity;
}