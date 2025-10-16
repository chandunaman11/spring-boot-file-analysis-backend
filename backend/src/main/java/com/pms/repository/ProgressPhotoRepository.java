package com.pms.repository;

import com.pms.entity.ProgressPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgressPhotoRepository extends JpaRepository<ProgressPhoto, Long> {
    
    List<ProgressPhoto> findByProjectId(Long projectId);
    
    List<ProgressPhoto> findByOrganizationId(Long organizationId);
    
    List<ProgressPhoto> findByProjectIdAndCategory(Long projectId, String category);
    
    List<ProgressPhoto> findByProjectIdAndTakenDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    List<ProgressPhoto> findByProjectIdOrderByTakenDateDesc(Long projectId);
    
    Long countByProjectId(Long projectId);
}