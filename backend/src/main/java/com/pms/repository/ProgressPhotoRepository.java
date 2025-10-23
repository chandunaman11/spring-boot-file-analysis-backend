package com.pms.repository;

import com.pms.entity.ProgressPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressPhotoRepository extends JpaRepository<ProgressPhoto, Long> {
    
    List<ProgressPhoto> findByProjectId(Long projectId);
    
    List<ProgressPhoto> findByOrganizationId(String organizationId);
    
    Optional<ProgressPhoto> findByIdAndOrganizationId(Long id, String organizationId);
    
    List<ProgressPhoto> findByProjectIdAndOrganizationId(Long projectId, String organizationId);
    
    List<ProgressPhoto> findByProjectIdAndCategory(Long projectId, String category);
    
    List<ProgressPhoto> findByProjectIdAndTakenDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    List<ProgressPhoto> findByProjectIdOrderByTakenDateDesc(Long projectId);
    
    Long countByProjectId(Long projectId);
}