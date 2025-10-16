package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem extends BaseEntity {
    
    @Column(unique = true, nullable = false)
    private String itemCode;
    
    @Column(nullable = false)
    private String itemName;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String category;
    
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal currentStock;
    
    @Column(nullable = false)
    private String unit;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal reorderLevel;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal maxStockLevel;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal unitCost;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    
    private String location;
    
    private String supplier;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockStatus status = StockStatus.ADEQUATE;
    
    private LocalDateTime lastRestocked;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    public enum StockStatus {
        ADEQUATE,
        LOW_STOCK,
        OUT_OF_STOCK,
        OVERSTOCKED
    }
}