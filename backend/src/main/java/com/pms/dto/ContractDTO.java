package com.pms.dto;

import com.pms.entity.Contract;
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
public class ContractDTO {
    private Long id;
    private String contractNumber;
    private String title;
    private String description;
    private Contract.ContractType type;
    private Contract.ContractStatus status;
    private String vendorName;
    private String vendorContact;
    private String vendorEmail;
    private BigDecimal contractValue;
    private BigDecimal paidAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate signedDate;
    private Long projectId;
    private String projectName;
    private String signedById;
    private String signedByName;
    private String terms;
    private String paymentTerms;
    private String documentPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}