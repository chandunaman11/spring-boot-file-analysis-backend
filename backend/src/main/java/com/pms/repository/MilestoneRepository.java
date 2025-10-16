package com.pms.repository;

import com.pms.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    
    List<Milestone> findByProjectId(Long projectId);
    
    List<Milestone> findByOrganizationId(Long organizationId);
    
    List<Milestone> findByProjectIdAndStatus(Long projectId, Milestone.MilestoneStatus status);
    
    List<Milestone> findByOrganizationIdAndStatus(Long organizationId, Milestone.MilestoneStatus status);
    
    List<Milestone> findByProjectIdAndDueDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndStatus(Long projectId, Milestone.MilestoneStatus status);
}