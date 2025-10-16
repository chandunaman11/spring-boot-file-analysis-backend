package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.DprEntryDTO;
import com.pms.service.DprEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dpr-entries")
@RequiredArgsConstructor
public class DprEntryController {

    private final DprEntryService dprEntryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DprEntryDTO>>> getAllDprEntries() {
        return ResponseEntity.ok(dprEntryService.getAllDprEntries());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<DprEntryDTO>>> getDprEntriesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(dprEntryService.getDprEntriesByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DprEntryDTO>> getDprEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(dprEntryService.getDprEntryById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DprEntryDTO>> createDprEntry(@RequestBody DprEntryDTO dprEntryDTO) {
        return ResponseEntity.ok(dprEntryService.createDprEntry(dprEntryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DprEntryDTO>> updateDprEntry(
            @PathVariable Long id,
            @RequestBody DprEntryDTO dprEntryDTO) {
        return ResponseEntity.ok(dprEntryService.updateDprEntry(id, dprEntryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDprEntry(@PathVariable Long id) {
        return ResponseEntity.ok(dprEntryService.deleteDprEntry(id));
    }
}