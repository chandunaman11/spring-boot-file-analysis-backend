package com.pms.dto;

import com.pms.entity.Milestone;
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
public class MilestoneDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private Milestone.MilestoneStatus status;
    private Integer progress;
    private Long projectId;
    private String projectName;
    private Long organizationId;
    private String organizationName;
    private LocalDate completedDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}