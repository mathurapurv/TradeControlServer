package com.apurv.tradecontrol.repository;

import com.apurv.tradecontrol.entity.Position;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends MongoRepository<Position, String> {
}
