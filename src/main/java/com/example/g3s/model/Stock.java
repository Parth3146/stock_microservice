package com.example.g3s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @AllArgsConstructor
    public enum TypeEnum {
        STOCK("stock");
        private final String value;
    }

    private TypeEnum type;

    @Id
    private String id;

    @Embedded
    private StockInfo attributes;

    public Stock(StockInfo attributes) {
        this.attributes = attributes;
    }
}

