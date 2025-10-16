package com.pms.repository;

import com.pms.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    
    List<ProjectMember> findByProjectId(Long projectId);
    
    List<ProjectMember> findByUserId(String userId);
    
    Optional<ProjectMember> findByProjectIdAndUserId(Long projectId, String userId);
    
    boolean existsByProjectIdAndUserId(Long projectId, String userId);
    
    void deleteByProjectIdAndUserId(Long projectId, String userId);
    
    long countByProjectId(Long projectId);
}