package com.zorr.portfolio.stock;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
@EnableMongoRepositories(basePackages = {"com.zorr.portfolio.stock.repository"})
public class MongoDbConfig extends AbstractMongoClientConfiguration{

    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Autowired
    private MappingMongoConverter mappingMongoConverter;

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @PostConstruct
    public void afterPropertiesSet(){
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

}
