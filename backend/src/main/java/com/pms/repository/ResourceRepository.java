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
    
    Optional<Resource> findByCode(String code);
    
    List<Resource> findByProjectId(Long projectId);
    
    List<Resource> findByOrganizationId(String organizationId);
    
    Optional<Resource> findByIdAndOrganizationId(Long id, String organizationId);
    
    List<Resource> findByProjectIdAndOrganizationId(Long projectId, String organizationId);
    
    List<Resource> findByProjectIdAndType(Long projectId, Resource.ResourceType type);
    
    List<Resource> findByProjectIdAndStatus(Long projectId, Resource.ResourceStatus status);
    
    List<Resource> findByProjectIdAndCategory(Long projectId, String category);
    
    List<Resource> findByOrganizationIdAndType(String organizationId, Resource.ResourceType type);
    
    @Query("SELECT r FROM Resource r WHERE r.project.id = :projectId AND " +
           "(LOWER(r.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(r.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Resource> searchResources(@Param("projectId") Long projectId,
                                   @Param("searchTerm") String searchTerm);
    
    Boolean existsByCode(String code);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndType(Long projectId, Resource.ResourceType type);
}