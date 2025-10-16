package com.pms.dto;

import com.pms.entity.QualityInspection;
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
public class QualityInspectionDTO {
    private Long id;
    private String inspectionNumber;
    private String title;
    private String description;
    private QualityInspection.InspectionType type;
    private QualityInspection.InspectionStatus status;
    private QualityInspection.InspectionResult result;
    private LocalDate scheduledDate;
    private LocalDate completedDate;
    private String location;
    private Long projectId;
    private String projectName;
    private String inspectorId;
    private String inspectorName;
    private String findings;
    private String recommendations;
    private String correctiveActions;
    private String attachments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}