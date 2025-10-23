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
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT rr FROM RiskRegister rr WHERE rr.project.organization.id = :organizationId")
    List<RiskRegister> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT rr FROM RiskRegister rr WHERE rr.id = :id AND rr.project.organization.id = :organizationId")
    Optional<RiskRegister> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT rr FROM RiskRegister rr WHERE rr.project.id = :projectId AND rr.project.organization.id = :organizationId")
    List<RiskRegister> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
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