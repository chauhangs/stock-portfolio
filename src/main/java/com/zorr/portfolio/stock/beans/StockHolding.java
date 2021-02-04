package com.zorr.portfolio.stock.beans;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockHolding {
    private String symbol;
    private int sharesOwned;
    private BigDecimal averagePrice;
   // private double currentValue;
    private BigDecimal investedValue;
}
