package com.zorr.portfolio.stock.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zorr.portfolio.stock.beans.Portfolio;
import com.zorr.portfolio.stock.service.StockService;

@RestController
public class PortfolioController {

    @Autowired
    StockService stockService;

    @GetMapping(value = {"/portfolio","/portfolio/{stock}"})
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable(required = false) String stock){
        Portfolio portfolio  =stockService.buildPortfolio(stock);
        return ResponseEntity.ok(portfolio);
    }

    @PutMapping("/portfolio/buy/{stock}")
    public ResponseEntity<Portfolio> buy(@PathVariable String stock,  @RequestParam(value="qty") int quantity, @RequestParam(value="price") BigDecimal price,  @RequestParam(value="txnDate") LocalDate txnDate){
        stockService.buy(stock, quantity, price );
        Portfolio portfolio  =stockService.buildPortfolio(stock);
        return ResponseEntity.ok(portfolio);
    }

    @PutMapping("/portfolio/sell/{stock}")
    public ResponseEntity<Portfolio> sell(@PathVariable String stock,  @RequestParam(value="qty") int quantity, @RequestParam(value="price") BigDecimal price, @RequestParam(value="txnDate") LocalDate txnDate){
        stockService.sell(stock, quantity, price );
        Portfolio portfolio  =stockService.buildPortfolio(stock);
        return ResponseEntity.ok(portfolio);
    }

}
