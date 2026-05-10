package com.apurv.tradecontrol.repository;

import com.apurv.tradecontrol.entity.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PriceRepository extends MongoRepository<Price, String> {
    
    List<Price> findByCusip(String cusip);
    
    Price findByCusipAndPriceDate(String cusip, Date priceDate);
    
    Price findFirstByCusipOrderByPriceDateDesc(String cusip);
}
