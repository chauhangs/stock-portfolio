package com.zorr.portfolio.stock.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    private String symbol;
    private float price;
    private String name;
}
