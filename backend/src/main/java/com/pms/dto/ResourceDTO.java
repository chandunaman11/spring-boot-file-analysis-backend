package com.pms.dto;

import com.pms.entity.Resource;
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
public class ResourceDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Resource.ResourceType type;
    private Resource.ResourceStatus status;
    private String category;
    private String unit;
    private BigDecimal quantity;
    private BigDecimal unitCost;
    private Long projectId;
    private String projectName;
    private Long organizationId;
    private String organizationName;
    private String supplier;
    private String location;
    private String specifications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}