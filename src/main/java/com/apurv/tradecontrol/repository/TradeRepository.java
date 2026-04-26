package com.apurv.tradecontrol.repository;

import com.apurv.tradecontrol.entity.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends MongoRepository<Trade, String> {
}
