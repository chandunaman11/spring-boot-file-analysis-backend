package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "procurement_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcurementOrder extends BaseEntity {
    
    @Column(unique = true, nullable = false)
    private String poNumber;
    
    @Column(nullable = false)
    private String supplierName;
    
    private String supplierContact;
    
    private String supplierEmail;
    
    @Column(nullable = false)
    private String materialName;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal quantity;
    
    @Column(nullable = false)
    private String unit;
    
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal unitPrice;
    
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount;
    
    @Column(nullable = false)
    private LocalDate orderDate;
    
    private LocalDate expectedDeliveryDate;
    
    private LocalDate actualDeliveryDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.DRAFT;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_by_id")
    private User orderedBy;
    
    @Column(columnDefinition = "TEXT")
    private String terms;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    public enum OrderStatus {
        DRAFT,
        SUBMITTED,
        APPROVED,
        ORDERED,
        PARTIALLY_DELIVERED,
        DELIVERED,
        CANCELLED
    }
}