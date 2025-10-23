package com.pms.repository;

import com.pms.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT r FROM Resource r WHERE r.project.organization.id = :organizationId")
    List<Resource> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT r FROM Resource r WHERE r.id = :id AND r.project.organization.id = :organizationId")
    Optional<Resource> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT r FROM Resource r WHERE r.project.id = :projectId AND r.project.organization.id = :organizationId")
    List<Resource> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
    @Query("SELECT r FROM Resource r WHERE r.task.id = :taskId AND r.project.organization.id = :organizationId")
    List<Resource> findByTaskIdAndOrganizationId(@Param("taskId") Long taskId, @Param("organizationId") Long organizationId);
    
    Optional<Resource> findByCode(String code);
    
    List<Resource> findByProjectId(Long projectId);
    
    List<Resource> findByProjectIdAndType(Long projectId, Resource.ResourceType type);
    
    List<Resource> findByProjectIdAndStatus(Long projectId, Resource.ResourceStatus status);
    
    List<Resource> findByProjectIdAndCategory(Long projectId, String category);
    
    List<Resource> findByOrganizationIdAndType(Long organizationId, Resource.ResourceType type);
    
    @Query("SELECT r FROM Resource r WHERE r.project.id = :projectId AND " +
           "(LOWER(r.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(r.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Resource> searchResources(@Param("projectId") Long projectId,
                                   @Param("searchTerm") String searchTerm);
    
    Boolean existsByCode(String code);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndType(Long projectId, Resource.ResourceType type);
}