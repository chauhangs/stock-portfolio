package com.zorr.portfolio.stock.beans;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

   private List<StockHolding> holdings;
   private BigDecimal currentValue;
   private BigDecimal investedValue;
}
