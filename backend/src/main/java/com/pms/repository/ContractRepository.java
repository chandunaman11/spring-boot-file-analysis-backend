package com.pms.repository;

import com.pms.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    
    Optional<Contract> findByContractNumber(String contractNumber);
    
    List<Contract> findByProjectId(Long projectId);
    
    List<Contract> findByProjectIdAndStatus(Long projectId, Contract.ContractStatus status);
    
    List<Contract> findByProjectIdAndType(Long projectId, Contract.ContractType type);
    
    List<Contract> findByVendorName(String vendorName);
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT c FROM Contract c WHERE c.project.organization.id = :organizationId")
    List<Contract> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT c FROM Contract c WHERE c.id = :id AND c.project.organization.id = :organizationId")
    Optional<Contract> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT c FROM Contract c WHERE c.project.id = :projectId AND c.project.organization.id = :organizationId")
    List<Contract> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
    @Query("SELECT c FROM Contract c WHERE c.project.id = :projectId AND " +
           "c.endDate BETWEEN :startDate AND :endDate")
    List<Contract> findExpiringContracts(@Param("projectId") Long projectId,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
    
    @Query("SELECT c FROM Contract c WHERE c.project.id = :projectId AND " +
           "(LOWER(c.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.vendorName) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Contract> searchContracts(@Param("projectId") Long projectId,
                                   @Param("searchTerm") String searchTerm);
    
    Boolean existsByContractNumber(String contractNumber);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndStatus(Long projectId, Contract.ContractStatus status);
}