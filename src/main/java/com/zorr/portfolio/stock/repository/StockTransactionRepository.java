package com.zorr.portfolio.stock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zorr.portfolio.stock.entity.StockTransaction;

public interface StockTransactionRepository extends MongoRepository<StockTransaction, String> {

}
