package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.QualityInspectionDTO;
import com.pms.entity.Project;
import com.pms.entity.QualityInspection;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.QualityInspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QualityInspectionService {

    private final QualityInspectionRepository qualityInspectionRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<QualityInspectionDTO>> getAllInspections() {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<QualityInspection> inspections = qualityInspectionRepository.findByOrganizationId(organizationId);
        List<QualityInspectionDTO> inspectionDTOs = inspections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(inspectionDTOs, "Quality inspections retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<QualityInspectionDTO>> getInspectionsByProject(Long projectId) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<QualityInspection> inspections = qualityInspectionRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<QualityInspectionDTO> inspectionDTOs = inspections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(inspectionDTOs, "Project inspections retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<QualityInspectionDTO> getInspectionById(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        QualityInspection inspection = qualityInspectionRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Quality inspection not found with id: " + id));
        return ApiResponse.success(convertToDTO(inspection), "Quality inspection retrieved successfully");
    }

    @Transactional
    public ApiResponse<QualityInspectionDTO> createInspection(QualityInspectionDTO inspectionDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(inspectionDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + inspectionDTO.getProjectId()));

        QualityInspection inspection = new QualityInspection();
        inspection.setProject(project);
        inspection.setOrganizationId(organizationId);
        inspection.setInspectionType(inspectionDTO.getInspectionType());
        inspection.setInspectionDate(inspectionDTO.getInspectionDate() != null ? inspectionDTO.getInspectionDate() : LocalDate.now());
        inspection.setInspector(inspectionDTO.getInspector());
        inspection.setLocation(inspectionDTO.getLocation());
        inspection.setChecklistItems(inspectionDTO.getChecklistItems());
        inspection.setFindings(inspectionDTO.getFindings());
        inspection.setStatus(inspectionDTO.getStatus());
        inspection.setRecommendations(inspectionDTO.getRecommendations());

        QualityInspection savedInspection = qualityInspectionRepository.save(inspection);
        return ApiResponse.success(convertToDTO(savedInspection), "Quality inspection created successfully");
    }

    @Transactional
    public ApiResponse<QualityInspectionDTO> updateInspection(Long id, QualityInspectionDTO inspectionDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        QualityInspection inspection = qualityInspectionRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Quality inspection not found with id: " + id));

        inspection.setInspectionType(inspectionDTO.getInspectionType());
        inspection.setInspectionDate(inspectionDTO.getInspectionDate());
        inspection.setInspector(inspectionDTO.getInspector());
        inspection.setLocation(inspectionDTO.getLocation());
        inspection.setChecklistItems(inspectionDTO.getChecklistItems());
        inspection.setFindings(inspectionDTO.getFindings());
        inspection.setStatus(inspectionDTO.getStatus());
        inspection.setRecommendations(inspectionDTO.getRecommendations());

        QualityInspection updatedInspection = qualityInspectionRepository.save(inspection);
        return ApiResponse.success(convertToDTO(updatedInspection), "Quality inspection updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteInspection(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        QualityInspection inspection = qualityInspectionRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Quality inspection not found with id: " + id));
        qualityInspectionRepository.delete(inspection);
        return ApiResponse.success(null, "Quality inspection deleted successfully");
    }

    private QualityInspectionDTO convertToDTO(QualityInspection inspection) {
        QualityInspectionDTO dto = new QualityInspectionDTO();
        dto.setId(inspection.getId());
        dto.setProjectId(inspection.getProject().getId());
        dto.setProjectName(inspection.getProject().getName());
        dto.setOrganizationId(inspection.getOrganizationId());
        dto.setInspectionType(inspection.getInspectionType());
        dto.setInspectionDate(inspection.getInspectionDate());
        dto.setInspector(inspection.getInspector());
        dto.setLocation(inspection.getLocation());
        dto.setChecklistItems(inspection.getChecklistItems());
        dto.setFindings(inspection.getFindings());
        dto.setStatus(inspection.getStatus());
        dto.setRecommendations(inspection.getRecommendations());
        dto.setCreatedAt(inspection.getCreatedAt());
        dto.setUpdatedAt(inspection.getUpdatedAt());
        return dto;
    }
}