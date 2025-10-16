package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.RiskRegisterDTO;
import com.pms.entity.Project;
import com.pms.entity.RiskRegister;
import com.pms.exception.ResourceNotFoundException;
import com.pms.filter.OrganizationContext;
import com.pms.repository.ProjectRepository;
import com.pms.repository.RiskRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RiskRegisterService {

    private final RiskRegisterRepository riskRegisterRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<RiskRegisterDTO>> getAllRisks() {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<RiskRegister> risks = riskRegisterRepository.findByOrganizationId(organizationId);
        List<RiskRegisterDTO> riskDTOs = risks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(riskDTOs, "Risks retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<RiskRegisterDTO>> getRisksByProject(Long projectId) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<RiskRegister> risks = riskRegisterRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<RiskRegisterDTO> riskDTOs = risks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(riskDTOs, "Project risks retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<RiskRegisterDTO> getRiskById(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        RiskRegister risk = riskRegisterRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Risk not found with id: " + id));
        return ApiResponse.success(convertToDTO(risk), "Risk retrieved successfully");
    }

    @Transactional
    public ApiResponse<RiskRegisterDTO> createRisk(RiskRegisterDTO riskDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(riskDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + riskDTO.getProjectId()));

        RiskRegister risk = new RiskRegister();
        risk.setProject(project);
        risk.setOrganizationId(organizationId);
        risk.setRiskDescription(riskDTO.getRiskDescription());
        risk.setRiskCategory(riskDTO.getRiskCategory());
        risk.setProbability(riskDTO.getProbability());
        risk.setImpact(riskDTO.getImpact());
        risk.setRiskScore(calculateRiskScore(riskDTO.getProbability(), riskDTO.getImpact()));
        risk.setMitigationStrategy(riskDTO.getMitigationStrategy());
        risk.setOwner(riskDTO.getOwner());
        risk.setStatus(riskDTO.getStatus());
        risk.setIdentifiedDate(riskDTO.getIdentifiedDate() != null ? riskDTO.getIdentifiedDate() : LocalDate.now());
        risk.setReviewDate(riskDTO.getReviewDate());

        RiskRegister savedRisk = riskRegisterRepository.save(risk);
        return ApiResponse.success(convertToDTO(savedRisk), "Risk created successfully");
    }

    @Transactional
    public ApiResponse<RiskRegisterDTO> updateRisk(Long id, RiskRegisterDTO riskDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        RiskRegister risk = riskRegisterRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Risk not found with id: " + id));

        risk.setRiskDescription(riskDTO.getRiskDescription());
        risk.setRiskCategory(riskDTO.getRiskCategory());
        risk.setProbability(riskDTO.getProbability());
        risk.setImpact(riskDTO.getImpact());
        risk.setRiskScore(calculateRiskScore(riskDTO.getProbability(), riskDTO.getImpact()));
        risk.setMitigationStrategy(riskDTO.getMitigationStrategy());
        risk.setOwner(riskDTO.getOwner());
        risk.setStatus(riskDTO.getStatus());
        risk.setIdentifiedDate(riskDTO.getIdentifiedDate());
        risk.setReviewDate(riskDTO.getReviewDate());

        RiskRegister updatedRisk = riskRegisterRepository.save(risk);
        return ApiResponse.success(convertToDTO(updatedRisk), "Risk updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteRisk(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        RiskRegister risk = riskRegisterRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Risk not found with id: " + id));
        riskRegisterRepository.delete(risk);
        return ApiResponse.success(null, "Risk deleted successfully");
    }

    private Integer calculateRiskScore(Integer probability, Integer impact) {
        return (probability != null && impact != null) ? probability * impact : 0;
    }

    private RiskRegisterDTO convertToDTO(RiskRegister risk) {
        RiskRegisterDTO dto = new RiskRegisterDTO();
        dto.setId(risk.getId());
        dto.setProjectId(risk.getProject().getId());
        dto.setProjectName(risk.getProject().getName());
        dto.setOrganizationId(risk.getOrganizationId());
        dto.setRiskDescription(risk.getRiskDescription());
        dto.setRiskCategory(risk.getRiskCategory());
        dto.setProbability(risk.getProbability());
        dto.setImpact(risk.getImpact());
        dto.setRiskScore(risk.getRiskScore());
        dto.setMitigationStrategy(risk.getMitigationStrategy());
        dto.setOwner(risk.getOwner());
        dto.setStatus(risk.getStatus());
        dto.setIdentifiedDate(risk.getIdentifiedDate());
        dto.setReviewDate(risk.getReviewDate());
        dto.setCreatedAt(risk.getCreatedAt());
        dto.setUpdatedAt(risk.getUpdatedAt());
        return dto;
    }
}