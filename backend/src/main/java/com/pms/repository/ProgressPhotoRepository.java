package com.pms.repository;

import com.pms.entity.ProgressPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressPhotoRepository extends JpaRepository<ProgressPhoto, Long> {
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT pp FROM ProgressPhoto pp WHERE pp.project.organization.id = :organizationId")
    List<ProgressPhoto> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT pp FROM ProgressPhoto pp WHERE pp.id = :id AND pp.project.organization.id = :organizationId")
    Optional<ProgressPhoto> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT pp FROM ProgressPhoto pp WHERE pp.project.id = :projectId AND pp.project.organization.id = :organizationId")
    List<ProgressPhoto> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
    List<ProgressPhoto> findByProjectIdAndCategory(Long projectId, String category);
    
    List<ProgressPhoto> findByProjectIdAndTakenDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    List<ProgressPhoto> findByProjectIdOrderByTakenDateDesc(Long projectId);
    
    Long countByProjectId(Long projectId);
}