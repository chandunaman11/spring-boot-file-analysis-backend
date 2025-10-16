package com.pms.dto;

import com.pms.entity.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDTO {
    private Long id;
    private String itemCode;
    private String itemName;
    private String description;
    private String category;
    private BigDecimal currentStock;
    private String unit;
    private BigDecimal reorderLevel;
    private BigDecimal maxStockLevel;
    private BigDecimal unitCost;
    private Long organizationId;
    private String organizationName;
    private String location;
    private String supplier;
    private InventoryItem.StockStatus status;
    private LocalDateTime lastRestocked;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}