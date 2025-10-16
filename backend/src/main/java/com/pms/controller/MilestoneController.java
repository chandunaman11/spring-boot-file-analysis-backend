package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.MilestoneDTO;
import com.pms.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MilestoneDTO>>> getAllMilestones() {
        return ResponseEntity.ok(milestoneService.getAllMilestones());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<MilestoneDTO>>> getMilestonesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(milestoneService.getMilestonesByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MilestoneDTO>> getMilestoneById(@PathVariable Long id) {
        return ResponseEntity.ok(milestoneService.getMilestoneById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MilestoneDTO>> createMilestone(@RequestBody MilestoneDTO milestoneDTO) {
        return ResponseEntity.ok(milestoneService.createMilestone(milestoneDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MilestoneDTO>> updateMilestone(
            @PathVariable Long id,
            @RequestBody MilestoneDTO milestoneDTO) {
        return ResponseEntity.ok(milestoneService.updateMilestone(id, milestoneDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMilestone(@PathVariable Long id) {
        return ResponseEntity.ok(milestoneService.deleteMilestone(id));
    }
}