package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dpr_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DprEntry extends BaseEntity {
    
    @Column(nullable = false)
    private LocalDate reportDate;
    
    @Column(nullable = false)
    private String workItem;
    
    private String wbsCode;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal plannedProgress;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal actualProgress;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal variance;
    
    private Integer resourcesUsed;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal productivity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;
    
    @Column(columnDefinition = "TEXT")
    private String remarks;
    
    @Column(columnDefinition = "TEXT")
    private String weatherConditions;
    
    private Integer laborCount;
    
    private Integer equipmentCount;
}