package com.pms.repository;

import com.pms.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, String> {
    
    Optional<Verification> findByIdentifierAndValue(String identifier, String value);
    
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
    
    void deleteByIdentifier(String identifier);
}