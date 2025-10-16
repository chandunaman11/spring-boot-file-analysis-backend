package com.pms.dto;

import com.pms.entity.RiskRegister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskRegisterDTO {
    private Long id;
    private String riskId;
    private String title;
    private String description;
    private RiskRegister.RiskCategory category;
    private RiskRegister.RiskProbability probability;
    private RiskRegister.RiskImpact impact;
    private RiskRegister.RiskStatus status;
    private String mitigationStrategy;
    private String contingencyPlan;
    private Long projectId;
    private String projectName;
    private String ownerId;
    private String ownerName;
    private LocalDate identifiedDate;
    private LocalDate reviewDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}