package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.DocumentDTO;
import com.pms.entity.Document;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.DocumentRepository;
import com.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<DocumentDTO>> getAllDocuments() {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Document> documents = documentRepository.findByOrganizationId(organizationId);
        List<DocumentDTO> documentDTOs = documents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(documentDTOs, "Documents retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<DocumentDTO>> getDocumentsByProject(Long projectId) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<Document> documents = documentRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<DocumentDTO> documentDTOs = documents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(documentDTOs, "Project documents retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<DocumentDTO> getDocumentById(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        Document document = documentRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
        return ApiResponse.success(convertToDTO(document), "Document retrieved successfully");
    }

    @Transactional
    public ApiResponse<DocumentDTO> createDocument(DocumentDTO documentDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(documentDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + documentDTO.getProjectId()));

        Document document = new Document();
        document.setProject(project);
        document.setOrganizationId(organizationId);
        document.setDocumentName(documentDTO.getDocumentName());
        document.setDocumentType(documentDTO.getDocumentType());
        document.setDocumentCategory(documentDTO.getDocumentCategory());
        document.setFilePath(documentDTO.getFilePath());
        document.setFileSize(documentDTO.getFileSize());
        document.setUploadedBy(documentDTO.getUploadedBy());
        document.setUploadedAt(LocalDateTime.now());
        document.setVersion(documentDTO.getVersion() != null ? documentDTO.getVersion() : "1.0");
        document.setDescription(documentDTO.getDescription());

        Document savedDocument = documentRepository.save(document);
        return ApiResponse.success(convertToDTO(savedDocument), "Document created successfully");
    }

    @Transactional
    public ApiResponse<DocumentDTO> updateDocument(Long id, DocumentDTO documentDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        Document document = documentRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));

        document.setDocumentName(documentDTO.getDocumentName());
        document.setDocumentType(documentDTO.getDocumentType());
        document.setDocumentCategory(documentDTO.getDocumentCategory());
        document.setFilePath(documentDTO.getFilePath());
        document.setFileSize(documentDTO.getFileSize());
        document.setVersion(documentDTO.getVersion());
        document.setDescription(documentDTO.getDescription());

        Document updatedDocument = documentRepository.save(document);
        return ApiResponse.success(convertToDTO(updatedDocument), "Document updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteDocument(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        Document document = documentRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
        documentRepository.delete(document);
        return ApiResponse.success(null, "Document deleted successfully");
    }

    private DocumentDTO convertToDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setProjectId(document.getProject().getId());
        dto.setProjectName(document.getProject().getName());
        dto.setOrganizationId(document.getOrganizationId());
        dto.setDocumentName(document.getDocumentName());
        dto.setDocumentType(document.getDocumentType());
        dto.setDocumentCategory(document.getDocumentCategory());
        dto.setFilePath(document.getFilePath());
        dto.setFileSize(document.getFileSize());
        dto.setUploadedBy(document.getUploadedBy());
        dto.setUploadedAt(document.getUploadedAt());
        dto.setVersion(document.getVersion());
        dto.setDescription(document.getDescription());
        dto.setCreatedAt(document.getCreatedAt());
        dto.setUpdatedAt(document.getUpdatedAt());
        return dto;
    }
}