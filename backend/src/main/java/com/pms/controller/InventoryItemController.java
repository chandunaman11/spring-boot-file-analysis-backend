package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.InventoryItemDTO;
import com.pms.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-items")
@RequiredArgsConstructor
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InventoryItemDTO>>> getAllInventoryItems() {
        return ResponseEntity.ok(inventoryItemService.getAllInventoryItems());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<InventoryItemDTO>>> getInventoryItemsByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(inventoryItemService.getInventoryItemsByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryItemDTO>> getInventoryItemById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryItemService.getInventoryItemById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InventoryItemDTO>> createInventoryItem(@RequestBody InventoryItemDTO inventoryItemDTO) {
        return ResponseEntity.ok(inventoryItemService.createInventoryItem(inventoryItemDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryItemDTO>> updateInventoryItem(
            @PathVariable Long id,
            @RequestBody InventoryItemDTO inventoryItemDTO) {
        return ResponseEntity.ok(inventoryItemService.updateInventoryItem(id, inventoryItemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInventoryItem(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryItemService.deleteInventoryItem(id));
    }
}