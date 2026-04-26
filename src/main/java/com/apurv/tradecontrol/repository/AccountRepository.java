package com.apurv.tradecontrol.repository;

import com.apurv.tradecontrol.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    
    Account findByAccountNumber(Long accountNumber);
}
