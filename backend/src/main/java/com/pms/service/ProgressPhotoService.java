package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.ProgressPhotoDTO;
import com.pms.entity.ProgressPhoto;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.ProgressPhotoRepository;
import com.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressPhotoService {

    private final ProgressPhotoRepository progressPhotoRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<ProgressPhotoDTO>> getAllProgressPhotos() {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<ProgressPhoto> photos = progressPhotoRepository.findByOrganizationId(organizationId);
        List<ProgressPhotoDTO> photoDTOs = photos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(photoDTOs, "Progress photos retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<ProgressPhotoDTO>> getProgressPhotosByProject(Long projectId) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<ProgressPhoto> photos = progressPhotoRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<ProgressPhotoDTO> photoDTOs = photos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(photoDTOs, "Project progress photos retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<ProgressPhotoDTO> getProgressPhotoById(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        ProgressPhoto photo = progressPhotoRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress photo not found with id: " + id));
        return ApiResponse.success(convertToDTO(photo), "Progress photo retrieved successfully");
    }

    @Transactional
    public ApiResponse<ProgressPhotoDTO> createProgressPhoto(ProgressPhotoDTO photoDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(photoDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + photoDTO.getProjectId()));

        ProgressPhoto photo = new ProgressPhoto();
        photo.setProject(project);
        photo.setOrganizationId(organizationId);
        photo.setPhotoTitle(photoDTO.getPhotoTitle());
        photo.setPhotoUrl(photoDTO.getPhotoUrl());
        photo.setThumbnailUrl(photoDTO.getThumbnailUrl());
        photo.setCaption(photoDTO.getCaption());
        photo.setLocation(photoDTO.getLocation());
        photo.setCapturedAt(photoDTO.getCapturedAt() != null ? photoDTO.getCapturedAt() : LocalDateTime.now());
        photo.setUploadedBy(photoDTO.getUploadedBy());
        photo.setTags(photoDTO.getTags());

        ProgressPhoto savedPhoto = progressPhotoRepository.save(photo);
        return ApiResponse.success(convertToDTO(savedPhoto), "Progress photo created successfully");
    }

    @Transactional
    public ApiResponse<ProgressPhotoDTO> updateProgressPhoto(Long id, ProgressPhotoDTO photoDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        ProgressPhoto photo = progressPhotoRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress photo not found with id: " + id));

        photo.setPhotoTitle(photoDTO.getPhotoTitle());
        photo.setPhotoUrl(photoDTO.getPhotoUrl());
        photo.setThumbnailUrl(photoDTO.getThumbnailUrl());
        photo.setCaption(photoDTO.getCaption());
        photo.setLocation(photoDTO.getLocation());
        photo.setCapturedAt(photoDTO.getCapturedAt());
        photo.setTags(photoDTO.getTags());

        ProgressPhoto updatedPhoto = progressPhotoRepository.save(photo);
        return ApiResponse.success(convertToDTO(updatedPhoto), "Progress photo updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteProgressPhoto(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        ProgressPhoto photo = progressPhotoRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress photo not found with id: " + id));
        progressPhotoRepository.delete(photo);
        return ApiResponse.success(null, "Progress photo deleted successfully");
    }

    private ProgressPhotoDTO convertToDTO(ProgressPhoto photo) {
        ProgressPhotoDTO dto = new ProgressPhotoDTO();
        dto.setId(photo.getId());
        dto.setProjectId(photo.getProject().getId());
        dto.setProjectName(photo.getProject().getName());
        dto.setOrganizationId(photo.getOrganizationId());
        dto.setPhotoTitle(photo.getPhotoTitle());
        dto.setPhotoUrl(photo.getPhotoUrl());
        dto.setThumbnailUrl(photo.getThumbnailUrl());
        dto.setCaption(photo.getCaption());
        dto.setLocation(photo.getLocation());
        dto.setCapturedAt(photo.getCapturedAt());
        dto.setUploadedBy(photo.getUploadedBy());
        dto.setTags(photo.getTags());
        dto.setCreatedAt(photo.getCreatedAt());
        dto.setUpdatedAt(photo.getUpdatedAt());
        return dto;
    }
}