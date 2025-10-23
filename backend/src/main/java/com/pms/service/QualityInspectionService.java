package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.QualityInspectionDTO;
import com.pms.entity.Project;
import com.pms.entity.QualityInspection;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.QualityInspectionRepository;
import com.pms.repository.ProjectRepository;
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
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<QualityInspectionDTO>> getAllInspections() {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<QualityInspection> inspections = qualityInspectionRepository.findByOrganizationId(organizationId);
        List<QualityInspectionDTO> inspectionDTOs = inspections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(inspectionDTOs, "Quality inspections retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<QualityInspectionDTO>> getInspectionsByProject(Long projectId) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<QualityInspection> inspections = qualityInspectionRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<QualityInspectionDTO> inspectionDTOs = inspections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(inspectionDTOs, "Project inspections retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<QualityInspectionDTO> getInspectionById(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        QualityInspection inspection = qualityInspectionRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Quality inspection not found with id: " + id));
        return ApiResponse.success(convertToDTO(inspection), "Quality inspection retrieved successfully");
    }

    @Transactional
    public ApiResponse<QualityInspectionDTO> createInspection(QualityInspectionDTO inspectionDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(inspectionDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + inspectionDTO.getProjectId()));

        QualityInspection inspection = new QualityInspection();
        inspection.setProject(project);
        inspection.setInspectionNumber(inspectionDTO.getInspectionNumber());
        inspection.setTitle(inspectionDTO.getTitle());
        inspection.setDescription(inspectionDTO.getDescription());
        inspection.setType(inspectionDTO.getType());
        inspection.setScheduledDate(inspectionDTO.getScheduledDate() != null ? inspectionDTO.getScheduledDate() : LocalDate.now());
        inspection.setCompletedDate(inspectionDTO.getCompletedDate());
        inspection.setLocation(inspectionDTO.getLocation());
        inspection.setFindings(inspectionDTO.getFindings());
        inspection.setStatus(inspectionDTO.getStatus());
        inspection.setResult(inspectionDTO.getResult());
        inspection.setRecommendations(inspectionDTO.getRecommendations());
        inspection.setCorrectiveActions(inspectionDTO.getCorrectiveActions());
        inspection.setAttachments(inspectionDTO.getAttachments());

        QualityInspection savedInspection = qualityInspectionRepository.save(inspection);
        return ApiResponse.success(convertToDTO(savedInspection), "Quality inspection created successfully");
    }

    @Transactional
    public ApiResponse<QualityInspectionDTO> updateInspection(Long id, QualityInspectionDTO inspectionDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        QualityInspection inspection = qualityInspectionRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Quality inspection not found with id: " + id));

        inspection.setInspectionNumber(inspectionDTO.getInspectionNumber());
        inspection.setTitle(inspectionDTO.getTitle());
        inspection.setDescription(inspectionDTO.getDescription());
        inspection.setType(inspectionDTO.getType());
        inspection.setScheduledDate(inspectionDTO.getScheduledDate());
        inspection.setCompletedDate(inspectionDTO.getCompletedDate());
        inspection.setLocation(inspectionDTO.getLocation());
        inspection.setFindings(inspectionDTO.getFindings());
        inspection.setStatus(inspectionDTO.getStatus());
        inspection.setResult(inspectionDTO.getResult());
        inspection.setRecommendations(inspectionDTO.getRecommendations());
        inspection.setCorrectiveActions(inspectionDTO.getCorrectiveActions());
        inspection.setAttachments(inspectionDTO.getAttachments());

        QualityInspection updatedInspection = qualityInspectionRepository.save(inspection);
        return ApiResponse.success(convertToDTO(updatedInspection), "Quality inspection updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteInspection(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
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
        dto.setInspectionNumber(inspection.getInspectionNumber());
        dto.setTitle(inspection.getTitle());
        dto.setDescription(inspection.getDescription());
        dto.setType(inspection.getType());
        dto.setStatus(inspection.getStatus());
        dto.setResult(inspection.getResult());
        dto.setScheduledDate(inspection.getScheduledDate());
        dto.setCompletedDate(inspection.getCompletedDate());
        dto.setLocation(inspection.getLocation());
        dto.setInspectorId(inspection.getInspector() != null ? inspection.getInspector().getId() : null);
        dto.setInspectorName(inspection.getInspector() != null ? inspection.getInspector().getUsername() : null);
        dto.setFindings(inspection.getFindings());
        dto.setRecommendations(inspection.getRecommendations());
        dto.setCorrectiveActions(inspection.getCorrectiveActions());
        dto.setAttachments(inspection.getAttachments());
        dto.setCreatedAt(inspection.getCreatedAt());
        dto.setUpdatedAt(inspection.getUpdatedAt());
        return dto;
    }
}