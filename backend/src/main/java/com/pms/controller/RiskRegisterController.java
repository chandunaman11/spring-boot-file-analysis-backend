package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.RiskRegisterDTO;
import com.pms.service.RiskRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risks")
@RequiredArgsConstructor
public class RiskRegisterController {

    private final RiskRegisterService riskRegisterService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RiskRegisterDTO>>> getAllRisks() {
        return ResponseEntity.ok(riskRegisterService.getAllRisks());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<RiskRegisterDTO>>> getRisksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(riskRegisterService.getRisksByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RiskRegisterDTO>> getRiskById(@PathVariable Long id) {
        return ResponseEntity.ok(riskRegisterService.getRiskById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RiskRegisterDTO>> createRisk(@RequestBody RiskRegisterDTO riskDTO) {
        return ResponseEntity.ok(riskRegisterService.createRisk(riskDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RiskRegisterDTO>> updateRisk(
            @PathVariable Long id,
            @RequestBody RiskRegisterDTO riskDTO) {
        return ResponseEntity.ok(riskRegisterService.updateRisk(id, riskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRisk(@PathVariable Long id) {
        return ResponseEntity.ok(riskRegisterService.deleteRisk(id));
    }
}