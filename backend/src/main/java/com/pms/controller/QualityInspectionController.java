package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.QualityInspectionDTO;
import com.pms.service.QualityInspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quality-inspections")
@RequiredArgsConstructor
public class QualityInspectionController {

    private final QualityInspectionService qualityInspectionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<QualityInspectionDTO>>> getAllInspections() {
        return ResponseEntity.ok(qualityInspectionService.getAllInspections());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<QualityInspectionDTO>>> getInspectionsByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(qualityInspectionService.getInspectionsByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QualityInspectionDTO>> getInspectionById(@PathVariable Long id) {
        return ResponseEntity.ok(qualityInspectionService.getInspectionById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<QualityInspectionDTO>> createInspection(@RequestBody QualityInspectionDTO inspectionDTO) {
        return ResponseEntity.ok(qualityInspectionService.createInspection(inspectionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<QualityInspectionDTO>> updateInspection(
            @PathVariable Long id,
            @RequestBody QualityInspectionDTO inspectionDTO) {
        return ResponseEntity.ok(qualityInspectionService.updateInspection(id, inspectionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInspection(@PathVariable Long id) {
        return ResponseEntity.ok(qualityInspectionService.deleteInspection(id));
    }
}