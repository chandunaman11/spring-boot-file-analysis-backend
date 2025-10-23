package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.ResourceDTO;
import com.pms.entity.Resource;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<ResourceDTO>> getAllResources() {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Resource> resources = resourceRepository.findByOrganizationId(organizationId);
        List<ResourceDTO> resourceDTOs = resources.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(resourceDTOs, "Resources retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<ResourceDTO>> getResourcesByProject(Long projectId) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Resource> resources = resourceRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<ResourceDTO> resourceDTOs = resources.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(resourceDTOs, "Project resources retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<ResourceDTO> getResourceById(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        Resource resource = resourceRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
        return ApiResponse.success(convertToDTO(resource), "Resource retrieved successfully");
    }

    @Transactional
    public ApiResponse<ResourceDTO> createResource(ResourceDTO resourceDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(resourceDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + resourceDTO.getProjectId()));

        Resource resource = new Resource();
        resource.setProject(project);
        resource.setOrganizationId(organizationId);
        resource.setResourceName(resourceDTO.getResourceName());
        resource.setResourceType(resourceDTO.getResourceType());
        resource.setResourceRole(resourceDTO.getResourceRole());
        resource.setAllocationPercentage(resourceDTO.getAllocationPercentage());
        resource.setStartDate(resourceDTO.getStartDate());
        resource.setEndDate(resourceDTO.getEndDate());
        resource.setCostPerDay(resourceDTO.getCostPerDay());
        resource.setSkills(resourceDTO.getSkills());
        resource.setContactInfo(resourceDTO.getContactInfo());

        Resource savedResource = resourceRepository.save(resource);
        return ApiResponse.success(convertToDTO(savedResource), "Resource created successfully");
    }

    @Transactional
    public ApiResponse<ResourceDTO> updateResource(Long id, ResourceDTO resourceDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        Resource resource = resourceRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        resource.setResourceName(resourceDTO.getResourceName());
        resource.setResourceType(resourceDTO.getResourceType());
        resource.setResourceRole(resourceDTO.getResourceRole());
        resource.setAllocationPercentage(resourceDTO.getAllocationPercentage());
        resource.setStartDate(resourceDTO.getStartDate());
        resource.setEndDate(resourceDTO.getEndDate());
        resource.setCostPerDay(resourceDTO.getCostPerDay());
        resource.setSkills(resourceDTO.getSkills());
        resource.setContactInfo(resourceDTO.getContactInfo());

        Resource updatedResource = resourceRepository.save(resource);
        return ApiResponse.success(convertToDTO(updatedResource), "Resource updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteResource(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        Resource resource = resourceRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
        resourceRepository.delete(resource);
        return ApiResponse.success(null, "Resource deleted successfully");
    }

    private ResourceDTO convertToDTO(Resource resource) {
        ResourceDTO dto = new ResourceDTO();
        dto.setId(resource.getId());
        dto.setProjectId(resource.getProject().getId());
        dto.setProjectName(resource.getProject().getName());
        dto.setOrganizationId(resource.getOrganizationId());
        dto.setResourceName(resource.getResourceName());
        dto.setResourceType(resource.getResourceType());
        dto.setResourceRole(resource.getResourceRole());
        dto.setAllocationPercentage(resource.getAllocationPercentage());
        dto.setStartDate(resource.getStartDate());
        dto.setEndDate(resource.getEndDate());
        dto.setCostPerDay(resource.getCostPerDay());
        dto.setSkills(resource.getSkills());
        dto.setContactInfo(resource.getContactInfo());
        dto.setCreatedAt(resource.getCreatedAt());
        dto.setUpdatedAt(resource.getUpdatedAt());
        return dto;
    }
}