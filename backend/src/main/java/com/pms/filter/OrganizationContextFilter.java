package com.pms.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class OrganizationContextFilter extends OncePerRequestFilter {
    
    private static final ThreadLocal<Long> currentOrganizationId = new ThreadLocal<>();
    
    public static Long getCurrentOrganizationId() {
        return currentOrganizationId.get();
    }
    
    public static void setCurrentOrganizationId(Long organizationId) {
        currentOrganizationId.set(organizationId);
    }
    
    public static void clear() {
        currentOrganizationId.remove();
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            String orgIdHeader = request.getHeader("X-Organization-Id");
            
            // Skip for auth endpoints
            if (isPublicEndpoint(request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }
            
            if (orgIdHeader != null && !orgIdHeader.isEmpty()) {
                try {
                    Long organizationId = Long.parseLong(orgIdHeader);
                    setCurrentOrganizationId(organizationId);
                    log.debug("Organization context set to: {}", organizationId);
                } catch (NumberFormatException e) {
                    log.error("Invalid X-Organization-Id header: {}", orgIdHeader);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid X-Organization-Id header");
                    return;
                }
            } else {
                log.warn("Missing X-Organization-Id header for protected endpoint: {}", request.getRequestURI());
                // For now, allow requests without org ID (will be enforced at service layer)
            }
            
            filterChain.doFilter(request, response);
        } finally {
            clear();
        }
    }
    
    private boolean isPublicEndpoint(String uri) {
        return uri.startsWith("/api/auth/") || 
               uri.startsWith("/h2-console") ||
               uri.startsWith("/actuator") ||
               uri.startsWith("/swagger-ui") ||
               uri.startsWith("/v3/api-docs");
    }
}