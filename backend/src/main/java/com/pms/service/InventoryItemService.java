package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.InventoryItemDTO;
import com.pms.entity.InventoryItem;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.filter.OrganizationContext;
import com.pms.repository.InventoryItemRepository;
import com.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<InventoryItemDTO>> getAllInventoryItems() {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<InventoryItem> items = inventoryItemRepository.findByOrganizationId(organizationId);
        List<InventoryItemDTO> itemDTOs = items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(itemDTOs, "Inventory items retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<InventoryItemDTO>> getInventoryItemsByProject(Long projectId) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        List<InventoryItem> items = inventoryItemRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<InventoryItemDTO> itemDTOs = items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(itemDTOs, "Project inventory items retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<InventoryItemDTO> getInventoryItemById(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        InventoryItem item = inventoryItemRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with id: " + id));
        return ApiResponse.success(convertToDTO(item), "Inventory item retrieved successfully");
    }

    @Transactional
    public ApiResponse<InventoryItemDTO> createInventoryItem(InventoryItemDTO itemDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(itemDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + itemDTO.getProjectId()));

        InventoryItem item = new InventoryItem();
        item.setProject(project);
        item.setOrganizationId(organizationId);
        item.setItemName(itemDTO.getItemName());
        item.setItemCode(itemDTO.getItemCode());
        item.setCategory(itemDTO.getCategory());
        item.setQuantity(itemDTO.getQuantity());
        item.setUnit(itemDTO.getUnit());
        item.setReorderLevel(itemDTO.getReorderLevel());
        item.setUnitCost(itemDTO.getUnitCost());
        item.setLocation(itemDTO.getLocation());
        item.setSupplier(itemDTO.getSupplier());
        item.setLastRestockDate(itemDTO.getLastRestockDate());

        InventoryItem savedItem = inventoryItemRepository.save(item);
        return ApiResponse.success(convertToDTO(savedItem), "Inventory item created successfully");
    }

    @Transactional
    public ApiResponse<InventoryItemDTO> updateInventoryItem(Long id, InventoryItemDTO itemDTO) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        InventoryItem item = inventoryItemRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with id: " + id));

        item.setItemName(itemDTO.getItemName());
        item.setItemCode(itemDTO.getItemCode());
        item.setCategory(itemDTO.getCategory());
        item.setQuantity(itemDTO.getQuantity());
        item.setUnit(itemDTO.getUnit());
        item.setReorderLevel(itemDTO.getReorderLevel());
        item.setUnitCost(itemDTO.getUnitCost());
        item.setLocation(itemDTO.getLocation());
        item.setSupplier(itemDTO.getSupplier());
        item.setLastRestockDate(itemDTO.getLastRestockDate());

        InventoryItem updatedItem = inventoryItemRepository.save(item);
        return ApiResponse.success(convertToDTO(updatedItem), "Inventory item updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteInventoryItem(Long id) {
        String organizationId = OrganizationContext.getCurrentOrganizationId();
        InventoryItem item = inventoryItemRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with id: " + id));
        inventoryItemRepository.delete(item);
        return ApiResponse.success(null, "Inventory item deleted successfully");
    }

    private InventoryItemDTO convertToDTO(InventoryItem item) {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(item.getId());
        dto.setProjectId(item.getProject().getId());
        dto.setProjectName(item.getProject().getName());
        dto.setOrganizationId(item.getOrganizationId());
        dto.setItemName(item.getItemName());
        dto.setItemCode(item.getItemCode());
        dto.setCategory(item.getCategory());
        dto.setQuantity(item.getQuantity());
        dto.setUnit(item.getUnit());
        dto.setReorderLevel(item.getReorderLevel());
        dto.setUnitCost(item.getUnitCost());
        dto.setLocation(item.getLocation());
        dto.setSupplier(item.getSupplier());
        dto.setLastRestockDate(item.getLastRestockDate());
        dto.setCreatedAt(item.getCreatedAt());
        dto.setUpdatedAt(item.getUpdatedAt());
        return dto;
    }
}