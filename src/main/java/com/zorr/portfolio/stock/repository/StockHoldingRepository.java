package com.zorr.portfolio.stock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zorr.portfolio.stock.entity.StockHolding;

public interface StockHoldingRepository extends MongoRepository<StockHolding, String> {

}
