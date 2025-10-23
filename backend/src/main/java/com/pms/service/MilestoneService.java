package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.MilestoneDTO;
import com.pms.entity.Milestone;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.MilestoneRepository;
import com.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<MilestoneDTO>> getAllMilestones() {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Milestone> milestones = milestoneRepository.findByOrganizationId(organizationId);
        List<MilestoneDTO> milestoneDTOs = milestones.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(milestoneDTOs, "Milestones retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<MilestoneDTO>> getMilestonesByProject(Long projectId) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Milestone> milestones = milestoneRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<MilestoneDTO> milestoneDTOs = milestones.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(milestoneDTOs, "Project milestones retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<MilestoneDTO> getMilestoneById(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        Milestone milestone = milestoneRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
        return ApiResponse.success(convertToDTO(milestone), "Milestone retrieved successfully");
    }

    @Transactional
    public ApiResponse<MilestoneDTO> createMilestone(MilestoneDTO milestoneDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(milestoneDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + milestoneDTO.getProjectId()));

        Milestone milestone = new Milestone();
        milestone.setProject(project);
        milestone.setName(milestoneDTO.getName());
        milestone.setDescription(milestoneDTO.getDescription());
        milestone.setDueDate(milestoneDTO.getDueDate());
        milestone.setCompletedDate(milestoneDTO.getCompletedDate());
        milestone.setStatus(milestoneDTO.getStatus());
        milestone.setProgress(milestoneDTO.getProgress() != null ? milestoneDTO.getProgress() : 0);
        milestone.setNotes(milestoneDTO.getNotes());

        Milestone savedMilestone = milestoneRepository.save(milestone);
        return ApiResponse.success(convertToDTO(savedMilestone), "Milestone created successfully");
    }

    @Transactional
    public ApiResponse<MilestoneDTO> updateMilestone(Long id, MilestoneDTO milestoneDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        Milestone milestone = milestoneRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));

        milestone.setName(milestoneDTO.getName());
        milestone.setDescription(milestoneDTO.getDescription());
        milestone.setDueDate(milestoneDTO.getDueDate());
        milestone.setCompletedDate(milestoneDTO.getCompletedDate());
        milestone.setStatus(milestoneDTO.getStatus());
        milestone.setProgress(milestoneDTO.getProgress());
        milestone.setNotes(milestoneDTO.getNotes());

        Milestone updatedMilestone = milestoneRepository.save(milestone);
        return ApiResponse.success(convertToDTO(updatedMilestone), "Milestone updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteMilestone(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        Milestone milestone = milestoneRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
        milestoneRepository.delete(milestone);
        return ApiResponse.success(null, "Milestone deleted successfully");
    }

    private MilestoneDTO convertToDTO(Milestone milestone) {
        MilestoneDTO dto = new MilestoneDTO();
        dto.setId(milestone.getId());
        dto.setProjectId(milestone.getProject().getId());
        dto.setProjectName(milestone.getProject().getName());
        dto.setOrganizationId(milestone.getOrganization() != null ? milestone.getOrganization().getId() : null);
        dto.setOrganizationName(milestone.getOrganization() != null ? milestone.getOrganization().getName() : null);
        dto.setName(milestone.getName());
        dto.setDescription(milestone.getDescription());
        dto.setDueDate(milestone.getDueDate());
        dto.setCompletedDate(milestone.getCompletedDate());
        dto.setStatus(milestone.getStatus());
        dto.setProgress(milestone.getProgress());
        dto.setNotes(milestone.getNotes());
        dto.setCreatedAt(milestone.getCreatedAt());
        dto.setUpdatedAt(milestone.getUpdatedAt());
        return dto;
    }
}