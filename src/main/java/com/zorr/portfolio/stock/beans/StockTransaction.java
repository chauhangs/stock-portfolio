package com.zorr.portfolio.stock.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockTransaction {

    private String symbol;
    private float price;
    private int quantity;
}
