# Multi-Tenancy Data Isolation Test Plan

## Overview
This document outlines the comprehensive testing strategy for multi-tenancy data isolation in the Spring Boot backend.

## Prerequisites
- Spring Boot backend running on `http://localhost:8080`
- H2 database initialized with schema
- OrganizationContextFilter active and registered

## Test Scenarios

### Scenario 1: Organization Context Enforcement
**Objective:** Verify that X-Organization-Id header is required for API access

**Test Cases:**
1. Make request WITHOUT X-Organization-Id header → Should return 403 Forbidden
2. Make request WITH valid X-Organization-Id → Should return 200 OK
3. Make request WITH invalid/non-existent X-Organization-Id → Should return 403 Forbidden

### Scenario 2: Cross-Organization Data Isolation
**Objective:** Verify users cannot access data from other organizations

**Setup:**
- Create Organization A (org-id-aaa)
- Create Organization B (org-id-bbb)
- Create User A in Organization A
- Create User B in Organization B
- Create Project in Organization A
- Create Project in Organization B

**Test Cases:**
1. User A requests projects with X-Organization-Id: org-id-aaa → Should see ONLY Org A projects
2. User B requests projects with X-Organization-Id: org-id-bbb → Should see ONLY Org B projects
3. User A requests projects with X-Organization-Id: org-id-bbb → Should return 403 (not a member)
4. User B requests projects with X-Organization-Id: org-id-aaa → Should return 403 (not a member)

### Scenario 3: Organization Membership Validation
**Objective:** Verify membership checks work correctly

**Test Cases:**
1. Create organization member with OWNER role → Full access
2. Create organization member with ADMIN role → Full access
3. Create organization member with MEMBER role → Full access
4. Remove member from organization → Access revoked immediately

### Scenario 4: Data Creation Isolation
**Objective:** Verify new data is correctly scoped to organization

**Test Cases:**
1. Create project in Org A → organizationId should be set to Org A's ID
2. Query projects from Org A → Should include newly created project
3. Query projects from Org B → Should NOT include Org A's project

### Scenario 5: Authentication Flow
**Objective:** Verify JWT tokens work with multi-tenancy

**Test Cases:**
1. Register user → User should be created with String UUID
2. Login user → Should receive valid JWT token
3. Use JWT token with X-Organization-Id → Should authenticate successfully
4. Use expired/invalid token → Should return 401 Unauthorized

## Test Execution Steps

### Step 1: Register Test Users
```bash
# Register User A
curl -X POST http://localhost:3000/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@orga.com","password":"password123"}'

# Register User B
curl -X POST http://localhost:3000/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Bob","email":"bob@orgb.com","password":"password123"}'
```

### Step 2: Login and Get Tokens
```bash
# Login User A
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"alice@orga.com","password":"password123"}'

# Login User B
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"bob@orgb.com","password":"password123"}'
```

### Step 3: Create Organizations
```bash
# Create Organization A (by User A)
curl -X POST http://localhost:8080/api/organizations \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <USER_A_TOKEN>" \
  -d '{"name":"Organization A","description":"Test Org A"}'

# Create Organization B (by User B)
curl -X POST http://localhost:8080/api/organizations \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <USER_B_TOKEN>" \
  -d '{"name":"Organization B","description":"Test Org B"}'
```

### Step 4: Test Project Creation with Organization Context
```bash
# User A creates project in Org A
curl -X POST http://localhost:3000/api/projects \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <USER_A_TOKEN>" \
  -H "X-Organization-Id: <ORG_A_ID>" \
  -d '{"name":"Project A1","description":"Project in Org A"}'

# User B creates project in Org B
curl -X POST http://localhost:3000/api/projects \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <USER_B_TOKEN>" \
  -H "X-Organization-Id: <ORG_B_ID>" \
  -d '{"name":"Project B1","description":"Project in Org B"}'
```

### Step 5: Test Data Isolation
```bash
# User A queries projects in Org A (should see Project A1)
curl -X GET http://localhost:3000/api/projects \
  -H "Authorization: Bearer <USER_A_TOKEN>" \
  -H "X-Organization-Id: <ORG_A_ID>"

# User B queries projects in Org B (should see Project B1)
curl -X GET http://localhost:3000/api/projects \
  -H "Authorization: Bearer <USER_B_TOKEN>" \
  -H "X-Organization-Id: <ORG_B_ID>"

# User A tries to access Org B (should fail with 403)
curl -X GET http://localhost:3000/api/projects \
  -H "Authorization: Bearer <USER_A_TOKEN>" \
  -H "X-Organization-Id: <ORG_B_ID>"

# User B tries to access Org A (should fail with 403)
curl -X GET http://localhost:3000/api/projects \
  -H "Authorization: Bearer <USER_B_TOKEN>" \
  -H "X-Organization-Id: <ORG_A_ID>"
```

### Step 6: Test Without Organization Context
```bash
# Request without X-Organization-Id (should fail with 403)
curl -X GET http://localhost:3000/api/projects \
  -H "Authorization: Bearer <USER_A_TOKEN>"
```

## Expected Results

### ✅ Pass Criteria
- [ ] Users can only access data from organizations they belong to
- [ ] X-Organization-Id header is enforced on all protected endpoints
- [ ] Cross-organization access attempts return 403 Forbidden
- [ ] Data created in one organization is not visible to other organizations
- [ ] Organization membership validation works correctly
- [ ] JWT authentication works with String user IDs

### ❌ Fail Criteria
- Users can see data from organizations they don't belong to
- Missing X-Organization-Id header doesn't return error
- Cross-organization access succeeds
- Data isolation is broken

## Security Checklist
- [ ] OrganizationContextFilter is registered in SecurityConfig
- [ ] X-Organization-Id header validation works
- [ ] OrganizationMemberRepository checks are enforced
- [ ] No SQL injection vulnerabilities in organization queries
- [ ] JWT tokens are properly validated
- [ ] Session management works correctly

## Known Limitations
1. Currently using H2 in-memory database (data lost on restart)
2. No rate limiting implemented
3. No audit logging for cross-organization access attempts

## Next Steps After Testing
1. Fix any identified security gaps
2. Add audit logging for access attempts
3. Implement rate limiting
4. Add integration tests
5. Test with PostgreSQL in production-like environment