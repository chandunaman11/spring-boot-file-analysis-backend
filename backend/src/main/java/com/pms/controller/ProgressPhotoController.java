package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.ProgressPhotoDTO;
import com.pms.service.ProgressPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress-photos")
@RequiredArgsConstructor
public class ProgressPhotoController {

    private final ProgressPhotoService progressPhotoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProgressPhotoDTO>>> getAllProgressPhotos() {
        return ResponseEntity.ok(progressPhotoService.getAllProgressPhotos());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<ProgressPhotoDTO>>> getProgressPhotosByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(progressPhotoService.getProgressPhotosByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgressPhotoDTO>> getProgressPhotoById(@PathVariable Long id) {
        return ResponseEntity.ok(progressPhotoService.getProgressPhotoById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProgressPhotoDTO>> createProgressPhoto(@RequestBody ProgressPhotoDTO progressPhotoDTO) {
        return ResponseEntity.ok(progressPhotoService.createProgressPhoto(progressPhotoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgressPhotoDTO>> updateProgressPhoto(
            @PathVariable Long id,
            @RequestBody ProgressPhotoDTO progressPhotoDTO) {
        return ResponseEntity.ok(progressPhotoService.updateProgressPhoto(id, progressPhotoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProgressPhoto(@PathVariable Long id) {
        return ResponseEntity.ok(progressPhotoService.deleteProgressPhoto(id));
    }
}