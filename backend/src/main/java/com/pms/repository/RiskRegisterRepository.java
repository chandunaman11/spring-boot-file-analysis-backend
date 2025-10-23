package com.pms.repository;

import com.pms.entity.RiskRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiskRegisterRepository extends JpaRepository<RiskRegister, Long> {
    
    Optional<RiskRegister> findByRiskId(String riskId);
    
    List<RiskRegister> findByProjectId(Long projectId);
    
    List<RiskRegister> findByOrganizationId(String organizationId);
    
    Optional<RiskRegister> findByIdAndOrganizationId(Long id, String organizationId);
    
    List<RiskRegister> findByProjectIdAndOrganizationId(Long projectId, String organizationId);
    
    List<RiskRegister> findByProjectIdAndStatus(Long projectId, RiskRegister.RiskStatus status);
    
    List<RiskRegister> findByProjectIdAndCategory(Long projectId, RiskRegister.RiskCategory category);
    
    List<RiskRegister> findByProjectIdAndProbability(Long projectId, RiskRegister.RiskProbability probability);
    
    List<RiskRegister> findByProjectIdAndImpact(Long projectId, RiskRegister.RiskImpact impact);
    
    List<RiskRegister> findByOwnerId(Long ownerId);
    
    @Query("SELECT rr FROM RiskRegister rr WHERE rr.project.id = :projectId AND " +
           "(rr.probability = 'HIGH' OR rr.probability = 'VERY_HIGH') AND " +
           "(rr.impact = 'HIGH' OR rr.impact = 'CRITICAL')")
    List<RiskRegister> findHighPriorityRisks(@Param("projectId") Long projectId);
    
    @Query("SELECT rr FROM RiskRegister rr WHERE rr.project.id = :projectId AND " +
           "(LOWER(rr.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(rr.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<RiskRegister> searchRisks(@Param("projectId") Long projectId,
                                   @Param("searchTerm") String searchTerm);
    
    Boolean existsByRiskId(String riskId);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndStatus(Long projectId, RiskRegister.RiskStatus status);
}