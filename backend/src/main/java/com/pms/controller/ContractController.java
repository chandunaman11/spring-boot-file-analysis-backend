package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.ContractDTO;
import com.pms.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContractDTO>>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<ContractDTO>>> getContractsByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(contractService.getContractsByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContractDTO>> getContractById(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getContractById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContractDTO>> createContract(@RequestBody ContractDTO contractDTO) {
        return ResponseEntity.ok(contractService.createContract(contractDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContractDTO>> updateContract(
            @PathVariable Long id,
            @RequestBody ContractDTO contractDTO) {
        return ResponseEntity.ok(contractService.updateContract(id, contractDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContract(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.deleteContract(id));
    }
}