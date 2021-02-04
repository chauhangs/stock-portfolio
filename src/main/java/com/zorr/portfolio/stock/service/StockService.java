package com.zorr.portfolio.stock.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zorr.portfolio.stock.beans.Portfolio;
import com.zorr.portfolio.stock.entity.StockHolding;
import com.zorr.portfolio.stock.entity.StockTransaction;
import com.zorr.portfolio.stock.repository.StockHoldingRepository;
import com.zorr.portfolio.stock.repository.StockTransactionRepository;

@Service
public class StockService {

    @Autowired
    StockTransactionRepository transactionRepository;

    @Autowired
    StockHoldingRepository repository;

    public Portfolio buildPortfolio(String stock) {
        Portfolio portfolio = new Portfolio();
        List<com.zorr.portfolio.stock.beans.StockHolding> portfolioHoldings = new ArrayList<>();
        List<StockHolding> holdings = new ArrayList<>();
        if(StringUtils.isEmpty(stock)) {
           holdings = repository.findAll();
        }else {
            Optional<StockHolding> holding = repository.findById(stock);
            if(holding.isPresent()) {
                holdings.add(holding.get());
            }
        }

        BigDecimal investedValue = new BigDecimal(0);
        BigDecimal currentValue = new BigDecimal(0);
        for(StockHolding holding : holdings) {
            investedValue = investedValue.add(holding.getInvestedValue());
            //currentValue += holding.getCurrentValue();
            portfolioHoldings.add(new com.zorr.portfolio.stock.beans.StockHolding(holding.getSymbol(), holding.getStockOwned(),
                                                                                  holding.getAveragePrice(), holding.getInvestedValue()));
        }
        portfolio.setInvestedValue(investedValue);
        portfolio.setCurrentValue(currentValue);
        portfolio.setHoldings(portfolioHoldings);
        return portfolio;
    }

    public void buy(String stock, int quantity, BigDecimal price, LocalDate txnDate) {
        StockTransaction tx = new StockTransaction(stock, quantity, price, StockTransaction.Type.BUY, txnDate);
        updateHolding(stock, quantity, price, true);
        transactionRepository.save(tx);
    }

    public void sell(String stock, int quantity, BigDecimal price, LocalDate txnDate) {
        StockTransaction tx = new StockTransaction(stock, quantity, price, StockTransaction.Type.BUY, txnDate);
        updateHolding(stock, quantity, price, false);
        transactionRepository.save(tx);
    }

    private void updateHolding(String stock, int quantity, BigDecimal price, boolean buy) {

        Optional<StockHolding> existing = repository.findById(stock);
        StockHolding stockHolding = new StockHolding();
        BigDecimal transactionValue =  price.multiply(BigDecimal.valueOf(quantity));
        if(existing.isPresent()) {
            stockHolding = existing.get();
            BigDecimal investedValue = stockHolding.getInvestedValue();

            if(buy) {
                int increasedStocks = stockHolding.getStockOwned() + quantity;
                BigDecimal averagePrice = (investedValue.add(transactionValue)).divide(BigDecimal.valueOf(increasedStocks),RoundingMode.HALF_EVEN);
                int updatedOwnedStockCount = stockHolding.getStockOwned() + quantity;
                stockHolding.setAveragePrice(averagePrice);
                stockHolding.setStockOwned(updatedOwnedStockCount);
                stockHolding.setInvestedValue(stockHolding.getInvestedValue().add(transactionValue));
            } else {
                int leftStocks = stockHolding.getStockOwned() - quantity;
                BigDecimal averagePrice = investedValue.subtract(transactionValue).divide(BigDecimal.valueOf(leftStocks),RoundingMode.HALF_EVEN);
                int updatedOwnedStockCount = stockHolding.getStockOwned() - quantity;
                stockHolding.setAveragePrice(averagePrice);
                stockHolding.setStockOwned(updatedOwnedStockCount);
                stockHolding.setInvestedValue(stockHolding.getInvestedValue().subtract(transactionValue));
            }
        } else if(buy) {
            stockHolding = new StockHolding(stock, quantity, price, transactionValue);

        }

        repository.save(stockHolding);
    }
}
