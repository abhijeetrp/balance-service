package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.model.Balance;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BalanceRepository extends MongoRepository<Balance, Long> {
    List<Balance> findByAccountId(String id);
    @Query("{'accountId': ?0,'id': ?1 }")
    Balance findByAccountIdOrBalanceId(String accountId, String id);
    @Query("{'accountId': ?0,'id': ?1 }")
    String deleteByAccountIdOrBalanceId(String accountId, String id);

}
