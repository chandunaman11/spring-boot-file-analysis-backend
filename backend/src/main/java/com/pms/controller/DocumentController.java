package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.DocumentDTO;
import com.pms.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DocumentDTO>>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<DocumentDTO>>> getDocumentsByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(documentService.getDocumentsByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentDTO>> getDocumentById(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DocumentDTO>> createDocument(@RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.createDocument(documentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentDTO>> updateDocument(
            @PathVariable Long id,
            @RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.updateDocument(id, documentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDocument(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.deleteDocument(id));
    }
}