package com.zorr.portfolio.stock.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "StockHolding")
public class StockHolding {

    @Id
    private String symbol;
    private int stockOwned;
    private BigDecimal averagePrice;
    private BigDecimal investedValue;
}
