package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress_photos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressPhoto extends BaseEntity {
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private String photoUrl;
    
    private String thumbnailUrl;
    
    private String location;
    
    private Double latitude;
    
    private Double longitude;
    
    @Column(nullable = false)
    private LocalDate takenDate;
    
    private String category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by_id")
    private User uploadedBy;
    
    @Column(columnDefinition = "TEXT")
    private String tags;
    
    private Long fileSize;
    
    private String mimeType;
}