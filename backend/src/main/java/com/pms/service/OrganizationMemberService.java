package com.pms.service;

import com.pms.entity.Organization;
import com.pms.entity.OrganizationMember;
import com.pms.entity.User;
import com.pms.repository.OrganizationMemberRepository;
import com.pms.repository.OrganizationRepository;
import com.pms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationMemberService {
    
    private final OrganizationMemberRepository organizationMemberRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public OrganizationMember addMember(Long organizationId, String userId, OrganizationMember.MemberRole role) {
        // Validate organization exists
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + organizationId));
        
        // Validate user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        // Check if membership already exists
        if (organizationMemberRepository.existsByOrganizationIdAndUserId(organizationId, userId)) {
            throw new RuntimeException("User is already a member of this organization");
        }
        
        OrganizationMember member = OrganizationMember.builder()
                .organization(organization)
                .userId(userId)
                .role(role)
                .build();
        
        return organizationMemberRepository.save(member);
    }
    
    public List<OrganizationMember> getOrganizationMembers(Long organizationId) {
        return organizationMemberRepository.findByOrganizationId(organizationId);
    }
    
    public List<OrganizationMember> getUserMemberships(String userId) {
        return organizationMemberRepository.findByUserId(userId);
    }
    
    public OrganizationMember getMembership(Long organizationId, String userId) {
        return organizationMemberRepository.findByOrganizationIdAndUserId(organizationId, userId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
    }
    
    @Transactional
    public OrganizationMember updateMemberRole(Long organizationId, String userId, OrganizationMember.MemberRole newRole) {
        OrganizationMember member = getMembership(organizationId, userId);
        member.setRole(newRole);
        return organizationMemberRepository.save(member);
    }
    
    @Transactional
    public void removeMember(Long organizationId, String userId) {
        if (!organizationMemberRepository.existsByOrganizationIdAndUserId(organizationId, userId)) {
            throw new RuntimeException("Membership not found");
        }
        organizationMemberRepository.deleteByOrganizationIdAndUserId(organizationId, userId);
    }
    
    public boolean isMember(Long organizationId, String userId) {
        return organizationMemberRepository.existsByOrganizationIdAndUserId(organizationId, userId);
    }
    
    public boolean hasRole(Long organizationId, String userId, OrganizationMember.MemberRole role) {
        return organizationMemberRepository.findByOrganizationIdAndUserId(organizationId, userId)
                .map(member -> member.getRole() == role)
                .orElse(false);
    }
}