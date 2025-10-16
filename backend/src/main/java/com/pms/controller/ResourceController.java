package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.ResourceDTO;
import com.pms.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResourceDTO>>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<ResourceDTO>>> getResourcesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(resourceService.getResourcesByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceDTO>> getResourceById(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.getResourceById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ResourceDTO>> createResource(@RequestBody ResourceDTO resourceDTO) {
        return ResponseEntity.ok(resourceService.createResource(resourceDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceDTO>> updateResource(
            @PathVariable Long id,
            @RequestBody ResourceDTO resourceDTO) {
        return ResponseEntity.ok(resourceService.updateResource(id, resourceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteResource(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.deleteResource(id));
    }
}