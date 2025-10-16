package com.pms.repository;

import com.pms.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    
    Optional<Session> findByToken(String token);
    
    List<Session> findByUserId(String userId);
    
    void deleteByUserId(String userId);
    
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
    
    List<Session> findByUserIdAndExpiresAtAfter(String userId, LocalDateTime dateTime);
}