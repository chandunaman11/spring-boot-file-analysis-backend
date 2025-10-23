package com.pms.repository;

import com.pms.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    
    List<Milestone> findByProjectId(Long projectId);
    
    List<Milestone> findByOrganizationId(String organizationId);
    
    Optional<Milestone> findByIdAndOrganizationId(Long id, String organizationId);
    
    List<Milestone> findByProjectIdAndOrganizationId(Long projectId, String organizationId);
    
    List<Milestone> findByProjectIdAndStatus(Long projectId, Milestone.MilestoneStatus status);
    
    List<Milestone> findByOrganizationIdAndStatus(String organizationId, Milestone.MilestoneStatus status);
    
    List<Milestone> findByProjectIdAndDueDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndStatus(Long projectId, Milestone.MilestoneStatus status);
}