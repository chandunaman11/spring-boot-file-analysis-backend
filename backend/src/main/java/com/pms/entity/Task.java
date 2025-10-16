package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity {
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TODO;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;
    
    @Column(nullable = false)
    private LocalDate dueDate;
    
    private LocalDate completedDate;
    
    private Integer estimatedHours;
    
    private Integer actualHours;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @Column(name = "assigned_to_id")
    private String assignedToId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_id", insertable = false, updatable = false)
    private User assignedTo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;
    
    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
    private Set<Task> subTasks = new HashSet<>();
    
    @Column(columnDefinition = "TEXT")
    private String tags;
    
    @Column(columnDefinition = "TEXT")
    private String attachments;
    
    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        ON_HOLD,
        COMPLETED,
        CANCELLED
    }
    
    public enum TaskPriority {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }
}