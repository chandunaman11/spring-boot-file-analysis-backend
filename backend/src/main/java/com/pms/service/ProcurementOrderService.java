package com.pms.service;

import com.pms.dto.ApiResponse;
import com.pms.dto.ProcurementOrderDTO;
import com.pms.entity.ProcurementOrder;
import com.pms.entity.Project;
import com.pms.exception.ResourceNotFoundException;
import com.pms.context.OrganizationContext;
import com.pms.repository.ProcurementOrderRepository;
import com.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcurementOrderService {

    private final ProcurementOrderRepository procurementOrderRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<ProcurementOrderDTO>> getAllProcurementOrders() {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<ProcurementOrder> orders = procurementOrderRepository.findByOrganizationId(organizationId);
        List<ProcurementOrderDTO> orderDTOs = orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(orderDTOs, "Procurement orders retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<ProcurementOrderDTO>> getProcurementOrdersByProject(Long projectId) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        List<ProcurementOrder> orders = procurementOrderRepository.findByProjectIdAndOrganizationId(projectId, organizationId);
        List<ProcurementOrderDTO> orderDTOs = orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ApiResponse.success(orderDTOs, "Project procurement orders retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ApiResponse<ProcurementOrderDTO> getProcurementOrderById(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        ProcurementOrder order = procurementOrderRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Procurement order not found with id: " + id));
        return ApiResponse.success(convertToDTO(order), "Procurement order retrieved successfully");
    }

    @Transactional
    public ApiResponse<ProcurementOrderDTO> createProcurementOrder(ProcurementOrderDTO orderDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        
        Project project = projectRepository.findByIdAndOrganizationId(orderDTO.getProjectId(), organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + orderDTO.getProjectId()));

        ProcurementOrder order = new ProcurementOrder();
        order.setProject(project);
        order.setPoNumber(orderDTO.getPoNumber());
        order.setSupplierName(orderDTO.getSupplierName());
        order.setItemDescription(orderDTO.getItemDescription());
        order.setQuantity(orderDTO.getQuantity());
        order.setUnitPrice(orderDTO.getUnitPrice());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderDate(orderDTO.getOrderDate() != null ? orderDTO.getOrderDate() : LocalDate.now());
        order.setExpectedDeliveryDate(orderDTO.getExpectedDeliveryDate());
        order.setActualDeliveryDate(orderDTO.getActualDeliveryDate());
        order.setStatus(orderDTO.getStatus());
        order.setPaymentStatus(orderDTO.getPaymentStatus());

        ProcurementOrder savedOrder = procurementOrderRepository.save(order);
        return ApiResponse.success(convertToDTO(savedOrder), "Procurement order created successfully");
    }

    @Transactional
    public ApiResponse<ProcurementOrderDTO> updateProcurementOrder(Long id, ProcurementOrderDTO orderDTO) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        ProcurementOrder order = procurementOrderRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Procurement order not found with id: " + id));

        order.setPoNumber(orderDTO.getPoNumber());
        order.setSupplierName(orderDTO.getSupplierName());
        order.setItemDescription(orderDTO.getItemDescription());
        order.setQuantity(orderDTO.getQuantity());
        order.setUnitPrice(orderDTO.getUnitPrice());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setExpectedDeliveryDate(orderDTO.getExpectedDeliveryDate());
        order.setActualDeliveryDate(orderDTO.getActualDeliveryDate());
        order.setStatus(orderDTO.getStatus());
        order.setPaymentStatus(orderDTO.getPaymentStatus());

        ProcurementOrder updatedOrder = procurementOrderRepository.save(order);
        return ApiResponse.success(convertToDTO(updatedOrder), "Procurement order updated successfully");
    }

    @Transactional
    public ApiResponse<Void> deleteProcurementOrder(Long id) {
        Long organizationId = OrganizationContext.getCurrentOrganizationId();
        ProcurementOrder order = procurementOrderRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Procurement order not found with id: " + id));
        procurementOrderRepository.delete(order);
        return ApiResponse.success(null, "Procurement order deleted successfully");
    }

    private ProcurementOrderDTO convertToDTO(ProcurementOrder order) {
        ProcurementOrderDTO dto = new ProcurementOrderDTO();
        dto.setId(order.getId());
        dto.setProjectId(order.getProject().getId());
        dto.setProjectName(order.getProject().getName());
        dto.setPoNumber(order.getPoNumber());
        dto.setSupplierName(order.getSupplierName());
        dto.setItemDescription(order.getItemDescription());
        dto.setQuantity(order.getQuantity());
        dto.setUnitPrice(order.getUnitPrice());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDate(order.getOrderDate());
        dto.setExpectedDeliveryDate(order.getExpectedDeliveryDate());
        dto.setActualDeliveryDate(order.getActualDeliveryDate());
        dto.setStatus(order.getStatus());
        dto.setPaymentStatus(order.getPaymentStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        return dto;
    }
}