package com.pms.dto;

import com.pms.entity.ProcurementOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcurementOrderDTO {
    private Long id;
    private String poNumber;
    private String supplierName;
    private String supplierContact;
    private String supplierEmail;
    private String materialName;
    private String description;
    private BigDecimal quantity;
    private String unit;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private LocalDate actualDeliveryDate;
    private ProcurementOrder.OrderStatus status;
    private Long projectId;
    private String projectName;
    private Long organizationId;
    private String organizationName;
    private String orderedById;
    private String orderedByName;
    private String terms;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}