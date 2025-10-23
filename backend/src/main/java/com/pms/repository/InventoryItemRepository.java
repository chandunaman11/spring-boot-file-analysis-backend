package com.pms.repository;

import com.pms.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    
    // Multi-tenancy support methods
    List<InventoryItem> findByOrganizationId(Long organizationId);
    
    Optional<InventoryItem> findByIdAndOrganizationId(Long id, Long organizationId);
    
    List<InventoryItem> findByProjectIdAndOrganizationId(Long projectId, Long organizationId);
    
    List<InventoryItem> findByWarehouseLocationAndOrganizationId(String warehouseLocation, Long organizationId);
    
    List<InventoryItem> findByQuantityLessThanAndOrganizationId(Integer quantity, Long organizationId);
    
    List<InventoryItem> findByOrganizationIdAndCategory(Long organizationId, String category);
    
    List<InventoryItem> findByOrganizationIdAndStatus(Long organizationId, InventoryItem.StockStatus status);
    
    @Query("SELECT i FROM InventoryItem i WHERE i.organization.id = :organizationId AND i.currentStock <= i.reorderLevel")
    List<InventoryItem> findLowStockItems(@Param("organizationId") Long organizationId);
    
    @Query("SELECT i FROM InventoryItem i WHERE i.organization.id = :organizationId AND " +
           "(LOWER(i.itemName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(i.itemCode) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<InventoryItem> searchItems(@Param("organizationId") Long organizationId, @Param("searchTerm") String searchTerm);
    
    Boolean existsByItemCode(String itemCode);
    
    Long countByOrganizationId(Long organizationId);
    
    Long countByOrganizationIdAndStatus(Long organizationId, InventoryItem.StockStatus status);
}