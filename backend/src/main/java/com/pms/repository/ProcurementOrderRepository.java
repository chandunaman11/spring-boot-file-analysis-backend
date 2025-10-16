package com.pms.repository;

import com.pms.entity.ProcurementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcurementOrderRepository extends JpaRepository<ProcurementOrder, Long> {
    
    Optional<ProcurementOrder> findByPoNumber(String poNumber);
    
    List<ProcurementOrder> findByProjectId(Long projectId);
    
    List<ProcurementOrder> findByOrganizationId(Long organizationId);
    
    List<ProcurementOrder> findByOrganizationIdAndStatus(Long organizationId, ProcurementOrder.OrderStatus status);
    
    List<ProcurementOrder> findByProjectIdAndStatus(Long projectId, ProcurementOrder.OrderStatus status);
    
    List<ProcurementOrder> findBySupplierName(String supplierName);
    
    Boolean existsByPoNumber(String poNumber);
    
    Long countByOrganizationId(Long organizationId);
    
    Long countByOrganizationIdAndStatus(Long organizationId, ProcurementOrder.OrderStatus status);
}