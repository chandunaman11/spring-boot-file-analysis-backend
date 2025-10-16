package com.pms.repository;

import com.pms.entity.DprEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DprEntryRepository extends JpaRepository<DprEntry, Long> {
    
    List<DprEntry> findByProjectId(Long projectId);
    
    List<DprEntry> findByOrganizationId(Long organizationId);
    
    List<DprEntry> findByProjectIdAndReportDate(Long projectId, LocalDate reportDate);
    
    List<DprEntry> findByProjectIdAndReportDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    List<DprEntry> findByProjectIdOrderByReportDateDesc(Long projectId);
    
    Optional<DprEntry> findTopByProjectIdOrderByReportDateDesc(Long projectId);
    
    Long countByProjectId(Long projectId);
}