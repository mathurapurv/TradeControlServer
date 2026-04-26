package com.apurv.tradecontrol.repository;

import com.apurv.tradecontrol.entity.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {
    
    Asset findByCusip(String cusip);
}
