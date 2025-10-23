package com.pms.repository;

import com.pms.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT m FROM Milestone m WHERE m.project.organization.id = :organizationId")
    List<Milestone> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT m FROM Milestone m WHERE m.id = :id AND m.project.organization.id = :organizationId")
    Optional<Milestone> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT m FROM Milestone m WHERE m.project.id = :projectId AND m.project.organization.id = :organizationId")
    List<Milestone> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
    @Query("SELECT m FROM Milestone m WHERE m.project.id = :projectId AND m.status = :status AND m.project.organization.id = :organizationId")
    List<Milestone> findByProjectIdAndStatusAndOrganizationId(@Param("projectId") Long projectId, @Param("status") Milestone.MilestoneStatus status, @Param("organizationId") Long organizationId);
    
    @Query("SELECT m FROM Milestone m WHERE m.project.organization.id = :organizationId AND m.status = :status")
    List<Milestone> findByOrganizationIdAndStatus(@Param("organizationId") Long organizationId, @Param("status") Milestone.MilestoneStatus status);
    
    List<Milestone> findByProjectIdAndStatus(Long projectId, Milestone.MilestoneStatus status);
    
    List<Milestone> findByProjectIdAndDueDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndStatus(Long projectId, Milestone.MilestoneStatus status);
}