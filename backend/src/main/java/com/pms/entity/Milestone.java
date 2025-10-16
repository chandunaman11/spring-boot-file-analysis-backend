package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "milestones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone extends BaseEntity {
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private LocalDate dueDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MilestoneStatus status = MilestoneStatus.NOT_STARTED;
    
    @Column(nullable = false)
    private Integer progress = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    
    private LocalDate completedDate;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    public enum MilestoneStatus {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED,
        DELAYED,
        CANCELLED
    }
}