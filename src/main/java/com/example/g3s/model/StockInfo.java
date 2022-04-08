package com.example.g3s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {

    private String supplier;

    @NotNull(message = "Stock name should not be null!")
    private String name;

    @Min(0)
    private Long quantity;
}