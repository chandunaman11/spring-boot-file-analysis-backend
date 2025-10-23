package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.ContractDTO;
import com.pms.entity.Contract;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.ContractRepository;
import com.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<ContractDTO>> getAllContracts() {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Contract> contracts = contractRepository.findByOrganizationId(organizationId);
        List<ContractDTO> contractDTOs = contracts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(contractDTOs, "Contracts retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<ContractDTO>> getContractsByProject(Long projectId) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Contract> contracts = contractRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<ContractDTO> contractDTOs = contracts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(contractDTOs, "Project contracts retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<ContractDTO> getContractById(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        Contract contract = contractRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + id));
        return ApiResponse.success(convertToDTO(contract), "Contract retrieved successfully");
    }

    @Transactional
    public ApiResponse<ContractDTO> createContract(ContractDTO contractDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(contractDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + contractDTO.getProjectId()));

        Contract contract = new Contract();
        contract.setProject(project);
        contract.setContractNumber(contractDTO.getContractNumber());
        contract.setTitle(contractDTO.getTitle());
        contract.setDescription(contractDTO.getDescription());
        contract.setType(contractDTO.getType());
        contract.setVendorName(contractDTO.getVendorName());
        contract.setVendorContact(contractDTO.getVendorContact());
        contract.setVendorEmail(contractDTO.getVendorEmail());
        contract.setContractValue(contractDTO.getContractValue());
        contract.setPaidAmount(contractDTO.getPaidAmount());
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setSignedDate(contractDTO.getSignedDate());
        contract.setStatus(contractDTO.getStatus());
        contract.setTerms(contractDTO.getTerms());
        contract.setPaymentTerms(contractDTO.getPaymentTerms());
        contract.setDocumentPath(contractDTO.getDocumentPath());

        Contract savedContract = contractRepository.save(contract);
        return ApiResponse.success(convertToDTO(savedContract), "Contract created successfully");
    }

    @Transactional
    public ApiResponse<ContractDTO> updateContract(Long id, ContractDTO contractDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        Contract contract = contractRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + id));

        contract.setContractNumber(contractDTO.getContractNumber());
        contract.setTitle(contractDTO.getTitle());
        contract.setDescription(contractDTO.getDescription());
        contract.setType(contractDTO.getType());
        contract.setVendorName(contractDTO.getVendorName());
        contract.setVendorContact(contractDTO.getVendorContact());
        contract.setVendorEmail(contractDTO.getVendorEmail());
        contract.setContractValue(contractDTO.getContractValue());
        contract.setPaidAmount(contractDTO.getPaidAmount());
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setSignedDate(contractDTO.getSignedDate());
        contract.setStatus(contractDTO.getStatus());
        contract.setTerms(contractDTO.getTerms());
        contract.setPaymentTerms(contractDTO.getPaymentTerms());
        contract.setDocumentPath(contractDTO.getDocumentPath());

        Contract updatedContract = contractRepository.save(contract);
        return ApiResponse.success(convertToDTO(updatedContract), "Contract updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteContract(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        Contract contract = contractRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + id));
        contractRepository.delete(contract);
        return ApiResponse.success(null, "Contract deleted successfully");
    }

    private ContractDTO convertToDTO(Contract contract) {
        ContractDTO dto = new ContractDTO();
        dto.setId(contract.getId());
        dto.setProjectId(contract.getProject().getId());
        dto.setProjectName(contract.getProject().getName());
        dto.setContractNumber(contract.getContractNumber());
        dto.setTitle(contract.getTitle());
        dto.setDescription(contract.getDescription());
        dto.setType(contract.getType());
        dto.setStatus(contract.getStatus());
        dto.setVendorName(contract.getVendorName());
        dto.setVendorContact(contract.getVendorContact());
        dto.setVendorEmail(contract.getVendorEmail());
        dto.setContractValue(contract.getContractValue());
        dto.setPaidAmount(contract.getPaidAmount());
        dto.setStartDate(contract.getStartDate());
        dto.setEndDate(contract.getEndDate());
        dto.setSignedDate(contract.getSignedDate());
        dto.setSignedById(contract.getSignedBy() != null ? contract.getSignedBy().getId() : null);
        dto.setSignedByName(contract.getSignedBy() != null ? contract.getSignedBy().getName() : null);
        dto.setTerms(contract.getTerms());
        dto.setPaymentTerms(contract.getPaymentTerms());
        dto.setDocumentPath(contract.getDocumentPath());
        dto.setCreatedAt(contract.getCreatedAt());
        dto.setUpdatedAt(contract.getUpdatedAt());
        return dto;
    }
}