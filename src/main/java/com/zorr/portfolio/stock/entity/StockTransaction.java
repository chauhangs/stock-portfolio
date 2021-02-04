package com.zorr.portfolio.stock.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(value = "StockTransaction")
public class StockTransaction {

    private String symbol;
    private int quantity;
    private BigDecimal price;
    private Type type;
    private LocalDate transactionDate;

    public enum Type{
        SELL, BUY
    }
}
