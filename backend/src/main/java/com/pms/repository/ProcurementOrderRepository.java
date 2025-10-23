package com.pms.repository;

import com.pms.entity.ProcurementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcurementOrderRepository extends JpaRepository<ProcurementOrder, Long> {
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT po FROM ProcurementOrder po WHERE po.project.organization.id = :organizationId")
    List<ProcurementOrder> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT po FROM ProcurementOrder po WHERE po.id = :id AND po.project.organization.id = :organizationId")
    Optional<ProcurementOrder> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT po FROM ProcurementOrder po WHERE po.project.id = :projectId AND po.project.organization.id = :organizationId")
    List<ProcurementOrder> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
    @Query("SELECT po FROM ProcurementOrder po WHERE po.status = :status AND po.project.organization.id = :organizationId")
    List<ProcurementOrder> findByStatusAndOrganizationId(@Param("status") ProcurementOrder.OrderStatus status, @Param("organizationId") Long organizationId);
    
    @Query("SELECT po FROM ProcurementOrder po WHERE po.project.organization.id = :organizationId AND po.status = :status")
    List<ProcurementOrder> findByOrganizationIdAndStatus(@Param("organizationId") Long organizationId, @Param("status") ProcurementOrder.OrderStatus status);
    
    List<ProcurementOrder> findByProjectId(Long projectId);
    
    List<ProcurementOrder> findByProjectIdAndStatus(Long projectId, ProcurementOrder.OrderStatus status);
    
    List<ProcurementOrder> findBySupplierName(String supplierName);
    
    Boolean existsByPoNumber(String poNumber);
    
    Long countByOrganizationId(Long organizationId);
    
    Long countByOrganizationIdAndStatus(Long organizationId, ProcurementOrder.OrderStatus status);
}