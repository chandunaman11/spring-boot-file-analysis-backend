package com.pms.controller;

import com.pms.dto.ApiResponse;
import com.pms.entity.OrganizationMember;
import com.pms.service.OrganizationMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations/{organizationId}/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Organization Members", description = "Organization membership management endpoints")
public class OrganizationMemberController {
    
    private final OrganizationMemberService organizationMemberService;
    
    @PostMapping
    @Operation(summary = "Add member to organization")
    public ResponseEntity<ApiResponse<OrganizationMember>> addMember(
            @PathVariable Long organizationId,
            @Valid @RequestBody AddMemberRequest request) {
        try {
            OrganizationMember member = organizationMemberService.addMember(
                    organizationId, 
                    request.getUserId(), 
                    request.getRole()
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(member));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping
    @Operation(summary = "Get organization members")
    public ResponseEntity<ApiResponse<List<OrganizationMember>>> getMembers(
            @PathVariable Long organizationId) {
        try {
            List<OrganizationMember> members = organizationMemberService.getOrganizationMembers(organizationId);
            return ResponseEntity.ok(ApiResponse.success(members));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/{userId}")
    @Operation(summary = "Get member details")
    public ResponseEntity<ApiResponse<OrganizationMember>> getMember(
            @PathVariable Long organizationId,
            @PathVariable String userId) {
        try {
            OrganizationMember member = organizationMemberService.getMembership(organizationId, userId);
            return ResponseEntity.ok(ApiResponse.success(member));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PutMapping("/{userId}")
    @Operation(summary = "Update member role")
    public ResponseEntity<ApiResponse<OrganizationMember>> updateMemberRole(
            @PathVariable Long organizationId,
            @PathVariable String userId,
            @Valid @RequestBody UpdateRoleRequest request) {
        try {
            OrganizationMember member = organizationMemberService.updateMemberRole(
                    organizationId, 
                    userId, 
                    request.getRole()
            );
            return ResponseEntity.ok(ApiResponse.success(member));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @DeleteMapping("/{userId}")
    @Operation(summary = "Remove member from organization")
    public ResponseEntity<ApiResponse<Void>> removeMember(
            @PathVariable Long organizationId,
            @PathVariable String userId) {
        try {
            organizationMemberService.removeMember(organizationId, userId);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddMemberRequest {
        private String userId;
        private OrganizationMember.MemberRole role;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRoleRequest {
        private OrganizationMember.MemberRole role;
    }
}