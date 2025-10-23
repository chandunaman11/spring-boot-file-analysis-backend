package com.pms.repository;

import com.pms.entity.QualityInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface QualityInspectionRepository extends JpaRepository<QualityInspection, Long> {
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT qi FROM QualityInspection qi WHERE qi.project.organization.id = :organizationId")
    List<QualityInspection> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT qi FROM QualityInspection qi WHERE qi.id = :id AND qi.project.organization.id = :organizationId")
    Optional<QualityInspection> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT qi FROM QualityInspection qi WHERE qi.project.id = :projectId AND qi.project.organization.id = :organizationId")
    List<QualityInspection> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
    Optional<QualityInspection> findByInspectionNumber(String inspectionNumber);
    
    List<QualityInspection> findByProjectId(Long projectId);
    
    List<QualityInspection> findByProjectIdAndStatus(Long projectId, QualityInspection.InspectionStatus status);
    
    List<QualityInspection> findByProjectIdAndType(Long projectId, QualityInspection.InspectionType type);
    
    List<QualityInspection> findByProjectIdAndResult(Long projectId, QualityInspection.InspectionResult result);
    
    List<QualityInspection> findByInspectorId(Long inspectorId);
    
    @Query("SELECT qi FROM QualityInspection qi WHERE qi.project.id = :projectId AND " +
           "qi.scheduledDate BETWEEN :startDate AND :endDate")
    List<QualityInspection> findByScheduledDateRange(@Param("projectId") Long projectId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);
    
    @Query("SELECT qi FROM QualityInspection qi WHERE qi.project.id = :projectId AND " +
           "(LOWER(qi.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(qi.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<QualityInspection> searchInspections(@Param("projectId") Long projectId,
                                              @Param("searchTerm") String searchTerm);
    
    Boolean existsByInspectionNumber(String inspectionNumber);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndStatus(Long projectId, QualityInspection.InspectionStatus status);
    
    Long countByProjectIdAndResult(Long projectId, QualityInspection.InspectionResult result);
}