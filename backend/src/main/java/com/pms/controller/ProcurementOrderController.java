package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.dto.ProcurementOrderDTO;
import com.pms.service.ProcurementOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procurement-orders")
@RequiredArgsConstructor
public class ProcurementOrderController {

    private final ProcurementOrderService procurementOrderService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProcurementOrderDTO>>> getAllProcurementOrders() {
        return ResponseEntity.ok(procurementOrderService.getAllProcurementOrders());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<ProcurementOrderDTO>>> getProcurementOrdersByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(procurementOrderService.getProcurementOrdersByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProcurementOrderDTO>> getProcurementOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(procurementOrderService.getProcurementOrderById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProcurementOrderDTO>> createProcurementOrder(@RequestBody ProcurementOrderDTO procurementOrderDTO) {
        return ResponseEntity.ok(procurementOrderService.createProcurementOrder(procurementOrderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProcurementOrderDTO>> updateProcurementOrder(
            @PathVariable Long id,
            @RequestBody ProcurementOrderDTO procurementOrderDTO) {
        return ResponseEntity.ok(procurementOrderService.updateProcurementOrder(id, procurementOrderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProcurementOrder(@PathVariable Long id) {
        return ResponseEntity.ok(procurementOrderService.deleteProcurementOrder(id));
    }
}