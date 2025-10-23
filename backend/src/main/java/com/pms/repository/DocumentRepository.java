package com.pms.repository;

import com.pms.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
    List<Document> findByProjectId(Long projectId);
    
    List<Document> findByProjectIdAndDocumentType(Long projectId, Document.DocumentType documentType);
    
    List<Document> findByProjectIdAndStatus(Long projectId, Document.DocumentStatus status);
    
    List<Document> findByProjectIdAndCategory(Long projectId, String category);
    
    List<Document> findByUploadedById(Long userId);
    
    List<Document> findByProjectIdAndIsLatestVersion(Long projectId, Boolean isLatestVersion);
    
    List<Document> findByParentDocumentId(Long parentDocumentId);
    
    // Multi-tenancy support methods (through project.organization)
    @Query("SELECT d FROM Document d WHERE d.project.organization.id = :organizationId")
    List<Document> findByOrganizationId(@Param("organizationId") Long organizationId);
    
    @Query("SELECT d FROM Document d WHERE d.id = :id AND d.project.organization.id = :organizationId")
    Optional<Document> findByIdAndOrganizationId(@Param("id") Long id, @Param("organizationId") Long organizationId);
    
    @Query("SELECT d FROM Document d WHERE d.project.id = :projectId AND d.project.organization.id = :organizationId")
    List<Document> findByProjectIdAndOrganizationId(@Param("projectId") Long projectId, @Param("organizationId") Long organizationId);
    
    @Query("SELECT d FROM Document d WHERE d.project.id = :projectId AND " +
           "(LOWER(d.fileName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(d.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Document> searchDocuments(@Param("projectId") Long projectId,
                                   @Param("searchTerm") String searchTerm);
    
    Long countByProjectId(Long projectId);
    
    Long countByProjectIdAndStatus(Long projectId, Document.DocumentStatus status);
}