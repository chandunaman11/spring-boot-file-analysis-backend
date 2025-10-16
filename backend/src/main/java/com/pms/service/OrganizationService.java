package com.pms.service;

import com.pms.entity.Organization;
import com.pms.filter.OrganizationContextFilter;
import com.pms.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    
    private final OrganizationRepository organizationRepository;
    
    @Transactional
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }
    
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));
    }
    
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
    
    @Transactional
    public Organization updateOrganization(Long id, Organization organizationDetails) {
        Organization organization = getOrganizationById(id);
        
        organization.setName(organizationDetails.getName());
        organization.setCode(organizationDetails.getCode());
        organization.setDescription(organizationDetails.getDescription());
        organization.setAddress(organizationDetails.getAddress());
        organization.setCity(organizationDetails.getCity());
        organization.setState(organizationDetails.getState());
        organization.setCountry(organizationDetails.getCountry());
        organization.setPostalCode(organizationDetails.getPostalCode());
        organization.setEmail(organizationDetails.getEmail());
        organization.setPhone(organizationDetails.getPhone());
        organization.setWebsite(organizationDetails.getWebsite());
        organization.setTaxId(organizationDetails.getTaxId());
        organization.setType(organizationDetails.getType());
        organization.setStatus(organizationDetails.getStatus());
        organization.setLogo(organizationDetails.getLogo());
        
        return organizationRepository.save(organization);
    }
    
    @Transactional
    public void deleteOrganization(Long id) {
        Organization organization = getOrganizationById(id);
        organizationRepository.delete(organization);
    }
}