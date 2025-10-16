package com.pms.repository;

import com.pms.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    
    List<Account> findByUserId(String userId);
    
    Optional<Account> findByProviderIdAndAccountId(String providerId, String accountId);
    
    void deleteByUserId(String userId);
}