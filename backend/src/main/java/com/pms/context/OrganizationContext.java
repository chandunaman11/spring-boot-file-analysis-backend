package com.pms.context;

import lombok.extern.slf4j.Slf4j;

/**
 * Thread-local storage for organization context in multi-tenant architecture.
 * Stores the current organization ID for the request lifecycle.
 */
@Slf4j
public class OrganizationContext {
    
    private static final ThreadLocal<Long> currentOrganizationId = new ThreadLocal<>();
    
    private OrganizationContext() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Get the current organization ID from thread-local storage
     * @return Current organization ID or null if not set
     */
    public static Long getCurrentOrganizationId() {
        Long orgId = currentOrganizationId.get();
        log.trace("Retrieved organization ID from context: {}", orgId);
        return orgId;
    }
    
    /**
     * Set the current organization ID in thread-local storage
     * @param organizationId The organization ID to set
     */
    public static void setCurrentOrganizationId(Long organizationId) {
        log.debug("Setting organization context to: {}", organizationId);
        currentOrganizationId.set(organizationId);
    }
    
    /**
     * Clear the organization context from thread-local storage.
     * Should always be called in a finally block to prevent memory leaks.
     */
    public static void clear() {
        Long orgId = currentOrganizationId.get();
        if (orgId != null) {
            log.debug("Clearing organization context for: {}", orgId);
        }
        currentOrganizationId.remove();
    }
    
    /**
     * Check if organization context is set
     * @return true if organization ID is set, false otherwise
     */
    public static boolean isSet() {
        return currentOrganizationId.get() != null;
    }
    
    /**
     * Get current organization ID or throw exception if not set
     * @return Current organization ID
     * @throws IllegalStateException if organization context is not set
     */
    public static Long requireOrganizationId() {
        Long orgId = getCurrentOrganizationId();
        if (orgId == null) {
            throw new IllegalStateException("Organization context is not set. Ensure X-Organization-Id header is provided.");
        }
        return orgId;
    }
}
