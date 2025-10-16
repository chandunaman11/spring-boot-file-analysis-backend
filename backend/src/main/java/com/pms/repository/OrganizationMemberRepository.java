package com.pms.repository;

import com.pms.entity.Organization;
import com.pms.entity.OrganizationMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember, Long> {
    
    List<OrganizationMember> findByOrganizationId(Long organizationId);
    
    List<OrganizationMember> findByUserId(String userId);
    
    Optional<OrganizationMember> findByOrganizationIdAndUserId(Long organizationId, String userId);
    
    boolean existsByOrganizationIdAndUserId(Long organizationId, String userId);
    
    void deleteByOrganizationIdAndUserId(Long organizationId, String userId);
    
    long countByOrganizationId(Long organizationId);
}