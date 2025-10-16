package com.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DprEntryDTO {
    private Long id;
    private LocalDate reportDate;
    private String workItem;
    private String wbsCode;
    private BigDecimal plannedProgress;
    private BigDecimal actualProgress;
    private BigDecimal variance;
    private Integer resourcesUsed;
    private BigDecimal productivity;
    private Long projectId;
    private String projectName;
    private Long organizationId;
    private String organizationName;
    private String createdByUserId;
    private String createdByUserName;
    private String remarks;
    private String weatherConditions;
    private Integer laborCount;
    private Integer equipmentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}