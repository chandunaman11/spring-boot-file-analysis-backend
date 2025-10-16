package com.pms.dto;

import com.pms.entity.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileUrl;
    private Long fileSize;
    private String mimeType;
    private Document.DocumentType documentType;
    private Document.DocumentStatus status;
    private String version;
    private String description;
    private String category;
    private String tags;
    private Long projectId;
    private String uploadedById;
    private String uploadedByName;
    private String approvedById;
    private String approvedByName;
    private LocalDateTime approvedAt;
    private Long parentDocumentId;
    private Boolean isLatestVersion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}