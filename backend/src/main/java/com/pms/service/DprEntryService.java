package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.DprEntryDTO;
import com.pms.entity.DprEntry;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.DprEntryRepository;
import com.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DprEntryService {

    private final DprEntryRepository dprEntryRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<DprEntryDTO>> getAllDprEntries() {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<DprEntry> entries = dprEntryRepository.findByOrganizationId(organizationId);
        List<DprEntryDTO> entryDTOs = entries.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(entryDTOs, "DPR entries retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<DprEntryDTO>> getDprEntriesByProject(Long projectId) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<DprEntry> entries = dprEntryRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<DprEntryDTO> entryDTOs = entries.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(entryDTOs, "Project DPR entries retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<DprEntryDTO> getDprEntryById(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        DprEntry entry = dprEntryRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("DPR entry not found with id: " + id));
        return ApiResponse.success(convertToDTO(entry), "DPR entry retrieved successfully");
    }

    @Transactional
    public ApiResponse<DprEntryDTO> createDprEntry(DprEntryDTO entryDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(entryDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + entryDTO.getProjectId()));

        DprEntry entry = new DprEntry();
        entry.setProject(project);
        entry.setReportDate(entryDTO.getReportDate() != null ? entryDTO.getReportDate() : LocalDate.now());
        entry.setWeatherCondition(entryDTO.getWeatherCondition());
        entry.setWorkDescription(entryDTO.getWorkDescription());
        entry.setManpowerCount(entryDTO.getManpowerCount());
        entry.setMachineryUsed(entryDTO.getMachineryUsed());
        entry.setMaterialsUsed(entryDTO.getMaterialsUsed());
        entry.setSafetyIncidents(entryDTO.getSafetyIncidents());
        entry.setProgressPercentage(entryDTO.getProgressPercentage());
        entry.setIssuesAndChallenges(entryDTO.getIssuesAndChallenges());
        entry.setPreparedBy(entryDTO.getPreparedBy());

        DprEntry savedEntry = dprEntryRepository.save(entry);
        return ApiResponse.success(convertToDTO(savedEntry), "DPR entry created successfully");
    }

    @Transactional
    public ApiResponse<DprEntryDTO> updateDprEntry(Long id, DprEntryDTO entryDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        DprEntry entry = dprEntryRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("DPR entry not found with id: " + id));

        entry.setReportDate(entryDTO.getReportDate());
        entry.setWeatherCondition(entryDTO.getWeatherCondition());
        entry.setWorkDescription(entryDTO.getWorkDescription());
        entry.setManpowerCount(entryDTO.getManpowerCount());
        entry.setMachineryUsed(entryDTO.getMachineryUsed());
        entry.setMaterialsUsed(entryDTO.getMaterialsUsed());
        entry.setSafetyIncidents(entryDTO.getSafetyIncidents());
        entry.setProgressPercentage(entryDTO.getProgressPercentage());
        entry.setIssuesAndChallenges(entryDTO.getIssuesAndChallenges());
        entry.setPreparedBy(entryDTO.getPreparedBy());

        DprEntry updatedEntry = dprEntryRepository.save(entry);
        return ApiResponse.success(convertToDTO(updatedEntry), "DPR entry updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteDprEntry(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        DprEntry entry = dprEntryRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("DPR entry not found with id: " + id));
        dprEntryRepository.delete(entry);
        return ApiResponse.success(null, "DPR entry deleted successfully");
    }

    private DprEntryDTO convertToDTO(DprEntry entry) {
        DprEntryDTO dto = new DprEntryDTO();
        dto.setId(entry.getId());
        dto.setProjectId(entry.getProject().getId());
        dto.setProjectName(entry.getProject().getName());
        dto.setReportDate(entry.getReportDate());
        dto.setWeatherCondition(entry.getWeatherCondition());
        dto.setWorkDescription(entry.getWorkDescription());
        dto.setManpowerCount(entry.getManpowerCount());
        dto.setMachineryUsed(entry.getMachineryUsed());
        dto.setMaterialsUsed(entry.getMaterialsUsed());
        dto.setSafetyIncidents(entry.getSafetyIncidents());
        dto.setProgressPercentage(entry.getProgressPercentage());
        dto.setIssuesAndChallenges(entry.getIssuesAndChallenges());
        dto.setPreparedBy(entry.getPreparedBy());
        dto.setCreatedAt(entry.getCreatedAt());
        dto.setUpdatedAt(entry.getUpdatedAt());
        return dto;
    }
}