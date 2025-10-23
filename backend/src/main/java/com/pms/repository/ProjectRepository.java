package com.pms.repository;

import com.pms.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    Optional<Project> findByCode(String code);
    
    List<Project> findByOrganizationId(Long organizationId);
    
    List<Project> findByOrganizationIdAndStatus(Long organizationId, Project.ProjectStatus status);
    
    List<Project> findByProjectManagerId(Long projectManagerId);
    
    Optional<Project> findByIdAndOrganizationId(Long id, String organizationId);
    
    @Query("SELECT p FROM Project p JOIN p.members m WHERE m.id = :userId")
    List<Project> findByMemberId(@Param("userId") Long userId);
    
    List<Project> findByOrganizationIdAndType(Long organizationId, Project.ProjectType type);
    
    @Query("SELECT p FROM Project p WHERE p.organization.id = :organizationId AND " +
           "p.startDate >= :startDate AND p.endDate <= :endDate")
    List<Project> findByDateRange(@Param("organizationId") Long organizationId,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);
    
    @Query("SELECT p FROM Project p WHERE p.organization.id = :organizationId AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.code) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Project> searchProjects(@Param("organizationId") Long organizationId,
                                 @Param("searchTerm") String searchTerm);
    
    Boolean existsByCode(String code);
    
    Long countByOrganizationId(Long organizationId);
    
    Long countByOrganizationIdAndStatus(Long organizationId, Project.ProjectStatus status);
}