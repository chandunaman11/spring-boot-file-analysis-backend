package com.pms.dto;

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
public class ProgressPhotoDTO {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
    private String thumbnailUrl;
    private String location;
    private Double latitude;
    private Double longitude;
    private LocalDate takenDate;
    private String category;
    private Long projectId;
    private String projectName;
    private Long organizationId;
    private String organizationName;
    private String uploadedById;
    private String uploadedByName;
    private String tags;
    private Long fileSize;
    private String mimeType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}