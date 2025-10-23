import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
    <div className="min-h-screen bg-gradient-to-b from-background to-muted">
      {/* Header */}
      <header className="border-b border-border bg-card/50 backdrop-blur-sm">
        <div className="container mx-auto px-6 py-4">
          <h1 className="text-2xl font-bold">Project Management System</h1>
          <p className="text-sm text-muted-foreground">Full-Stack Spring Boot + Next.js Application</p>
        </div>
      </header>

      {/* Main Content */}
      <main className="container mx-auto px-6 py-12">
        {/* Hero Section */}
        <div className="mb-12 text-center">
          <div className="mb-6 flex justify-center gap-8">
            <div className="flex flex-col items-center gap-2">
              <div className="rounded-lg bg-primary/10 p-4">
                <svg className="h-12 w-12 text-primary" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 12h14M5 12a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v4a2 2 0 01-2 2M5 12a2 2 0 00-2 2v4a2 2 0 002 2h14a2 2 0 002-2v-4a2 2 0 00-2-2m-2-4h.01M17 16h.01" />
                </svg>
              </div>
              <span className="text-sm font-medium">Spring Boot 3.2</span>
            </div>
            <div className="flex items-center">
              <svg className="h-8 w-8 text-muted-foreground" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 7l5 5m0 0l-5 5m5-5H6" />
              </svg>
            </div>
            <div className="flex flex-col items-center gap-2">
              <div className="rounded-lg bg-primary/10 p-4">
                <Image className="dark:invert" src="/next.svg" alt="Next.js" width={48} height={48} />
              </div>
              <span className="text-sm font-medium">Next.js 15</span>
            </div>
          </div>
          <h2 className="mb-3 text-4xl font-bold">Construction Project Management</h2>
          <p className="mx-auto max-w-2xl text-lg text-muted-foreground">
            Complete full-stack application with Spring Boot REST API backend and Next.js frontend
          </p>
        </div>

        <div className="grid gap-8 lg:grid-cols-2">
          {/* Spring Boot Backend Card */}
          <div className="rounded-xl border border-border bg-card p-6 shadow-sm">
            <div className="mb-4 flex items-center gap-3">
              <div className="rounded-lg bg-green-500/10 p-2">
                <svg className="h-6 w-6 text-green-600 dark:text-green-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold">Spring Boot Backend</h3>
              <span className="ml-auto rounded-full bg-green-500/10 px-3 py-1 text-xs font-medium text-green-600 dark:text-green-400">
                100% Complete
              </span>
            </div>
            
            <p className="mb-4 text-sm text-muted-foreground">
              Full REST API with multi-tenancy support, JWT authentication, and comprehensive CRUD operations
            </p>

            <div className="mb-4 space-y-2 rounded-lg bg-muted/50 p-4">
              <h4 className="text-sm font-semibold">📂 Location</h4>
              <code className="block text-xs text-foreground/80">/backend/</code>
              
              <h4 className="mt-3 text-sm font-semibold">🚀 Quick Start</h4>
              <div className="space-y-1 text-xs text-foreground/80">
                <p>1. Navigate to backend folder</p>
                <p>2. Run: <code className="rounded bg-background px-2 py-1">mvn spring-boot:run</code></p>
                <p>3. API available at: <code className="rounded bg-background px-2 py-1">http://localhost:8080</code></p>
              </div>
            </div>

            <div className="mb-4">
              <h4 className="mb-2 text-sm font-semibold">✨ Features</h4>
              <div className="grid grid-cols-2 gap-2 text-xs">
                <div className="flex items-center gap-1">
                  <span className="text-green-600 dark:text-green-400">✓</span>
                  <span>Spring Security + JWT</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-green-600 dark:text-green-400">✓</span>
                  <span>Multi-Tenancy</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-green-600 dark:text-green-400">✓</span>
                  <span>PostgreSQL/H2</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-green-600 dark:text-green-400">✓</span>
                  <span>OpenAPI/Swagger</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-green-600 dark:text-green-400">✓</span>
                  <span>15 Complete Modules</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-green-600 dark:text-green-400">✓</span>
                  <span>90+ API Endpoints</span>
                </div>
              </div>
            </div>

            <Link 
              href="#modules" 
              className="inline-flex items-center gap-2 rounded-lg bg-primary px-4 py-2 text-sm font-medium text-primary-foreground transition-colors hover:bg-primary/90"
            >
              View All Modules
              <svg className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5l7 7-7 7" />
              </svg>
            </Link>
          </div>

          {/* Next.js Frontend Card */}
          <div className="rounded-xl border border-border bg-card p-6 shadow-sm">
            <div className="mb-4 flex items-center gap-3">
              <div className="rounded-lg bg-blue-500/10 p-2">
                <svg className="h-6 w-6 text-blue-600 dark:text-blue-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9.75 17L9 20l-1 1h8l-1-1-.75-3M3 13h18M5 17h14a2 2 0 002-2V5a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold">Next.js Frontend</h3>
              <span className="ml-auto rounded-full bg-blue-500/10 px-3 py-1 text-xs font-medium text-blue-600 dark:text-blue-400">
                Ready
              </span>
            </div>
            
            <p className="mb-4 text-sm text-muted-foreground">
              Modern React framework with TypeScript, Tailwind CSS, and Server Components
            </p>

            <div className="mb-4 space-y-2 rounded-lg bg-muted/50 p-4">
              <h4 className="text-sm font-semibold">📂 Location</h4>
              <code className="block text-xs text-foreground/80">/src/</code>
              
              <h4 className="mt-3 text-sm font-semibold">🚀 Quick Start</h4>
              <div className="space-y-1 text-xs text-foreground/80">
                <p>Already running in this environment!</p>
                <p>API Routes: <code className="rounded bg-background px-2 py-1">/src/app/api/</code></p>
                <p>Components: <code className="rounded bg-background px-2 py-1">/src/components/</code></p>
              </div>
            </div>

            <div className="mb-4">
              <h4 className="mb-2 text-sm font-semibold">✨ Features</h4>
              <div className="grid grid-cols-2 gap-2 text-xs">
                <div className="flex items-center gap-1">
                  <span className="text-blue-600 dark:text-blue-400">✓</span>
                  <span>TypeScript</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-blue-600 dark:text-blue-400">✓</span>
                  <span>Tailwind CSS</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-blue-600 dark:text-blue-400">✓</span>
                  <span>Server Components</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-blue-600 dark:text-blue-400">✓</span>
                  <span>App Router</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-blue-600 dark:text-blue-400">✓</span>
                  <span>API Routes</span>
                </div>
                <div className="flex items-center gap-1">
                  <span className="text-blue-600 dark:text-blue-400">✓</span>
                  <span>Dark Mode</span>
                </div>
              </div>
            </div>

            <a 
              href="https://nextjs.org/docs" 
              target="_blank"
              rel="noopener noreferrer"
              className="inline-flex items-center gap-2 rounded-lg border border-border px-4 py-2 text-sm font-medium transition-colors hover:bg-accent"
            >
              Next.js Documentation
              <svg className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14" />
              </svg>
            </a>
          </div>
        </div>

        {/* Backend Modules Section */}
        <div id="modules" className="mt-12">
          <h3 className="mb-6 text-2xl font-bold">Backend API Modules</h3>
          
          <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
            {[
              { name: "Authentication", endpoint: "/api/auth", description: "JWT-based authentication system" },
              { name: "Organizations", endpoint: "/api/organizations", description: "Multi-tenant organization management" },
              { name: "Projects", endpoint: "/api/projects", description: "Construction project tracking" },
              { name: "Tasks", endpoint: "/api/tasks", description: "Task and assignment management" },
              { name: "Documents", endpoint: "/api/documents", description: "File metadata and versioning" },
              { name: "Contracts", endpoint: "/api/contracts", description: "Vendor contract management" },
              { name: "Quality Inspections", endpoint: "/api/quality-inspections", description: "Quality control and checklists" },
              { name: "Resources", endpoint: "/api/resources", description: "Resource allocation tracking" },
              { name: "Risk Register", endpoint: "/api/risks", description: "Risk assessment and mitigation" },
              { name: "Milestones", endpoint: "/api/milestones", description: "Project milestone tracking" },
              { name: "DPR Entries", endpoint: "/api/dpr-entries", description: "Daily progress reports" },
              { name: "Progress Photos", endpoint: "/api/progress-photos", description: "Photo documentation" },
              { name: "Procurement", endpoint: "/api/procurement-orders", description: "Purchase order management" },
              { name: "Inventory", endpoint: "/api/inventory-items", description: "Stock and reorder tracking" },
            ].map((module) => (
              <div key={module.endpoint} className="rounded-lg border border-border bg-card p-4 transition-colors hover:bg-accent/50">
                <h4 className="mb-1 font-semibold">{module.name}</h4>
                <code className="mb-2 block text-xs text-muted-foreground">{module.endpoint}</code>
                <p className="text-xs text-muted-foreground">{module.description}</p>
              </div>
            ))}
          </div>
        </div>

        {/* Documentation Links */}
        <div className="mt-12 rounded-xl border border-border bg-card p-6">
          <h3 className="mb-4 text-xl font-semibold">📚 Documentation</h3>
          <div className="grid gap-4 md:grid-cols-3">
            <a
              href="/IMPLEMENTATION_COMPLETE_SUMMARY.md"
              className="flex items-center gap-3 rounded-lg border border-border p-4 transition-colors hover:bg-accent"
            >
              <svg className="h-5 w-5 text-primary" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
              </svg>
              <div>
                <div className="font-medium">Implementation Summary</div>
                <div className="text-xs text-muted-foreground">Complete feature list</div>
              </div>
            </a>
            
            <a
              href="/backend/README.md"
              className="flex items-center gap-3 rounded-lg border border-border p-4 transition-colors hover:bg-accent"
            >
              <svg className="h-5 w-5 text-primary" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M10 20l4-16m4 4l4 4-4 4M6 16l-4-4 4-4" />
              </svg>
              <div>
                <div className="font-medium">Backend README</div>
                <div className="text-xs text-muted-foreground">Setup instructions</div>
              </div>
            </a>
            
            <a
              href="/backend/pom.xml"
              className="flex items-center gap-3 rounded-lg border border-border p-4 transition-colors hover:bg-accent"
            >
              <svg className="h-5 w-5 text-primary" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M7 21a4 4 0 01-4-4V5a2 2 0 012-2h4a2 2 0 012 2v12a4 4 0 01-4 4zm0 0h12a2 2 0 002-2v-4a2 2 0 00-2-2h-2.343M11 7.343l1.657-1.657a2 2 0 012.828 0l2.829 2.829a2 2 0 010 2.828l-8.486 8.485M7 17h.01" />
              </svg>
              <div>
                <div className="font-medium">Maven POM</div>
                <div className="text-xs text-muted-foreground">Dependencies config</div>
              </div>
            </a>
          </div>
        </div>

        {/* Setup Instructions */}
        <div className="mt-12 rounded-xl border-2 border-primary/20 bg-primary/5 p-6">
          <h3 className="mb-4 text-xl font-semibold">🛠️ Running the Full Stack</h3>
          <div className="grid gap-6 md:grid-cols-2">
            <div>
              <h4 className="mb-2 font-semibold text-primary">Backend Setup</h4>
              <ol className="space-y-2 text-sm">
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">1.</span>
                  <span>Open <code className="rounded bg-background px-2 py-0.5">backend/</code> in IntelliJ IDEA or Eclipse</span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">2.</span>
                  <span>Configure database in <code className="rounded bg-background px-2 py-0.5">application.properties</code></span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">3.</span>
                  <span>Run <code className="rounded bg-background px-2 py-0.5">mvn clean install</code></span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">4.</span>
                  <span>Start application: <code className="rounded bg-background px-2 py-0.5">mvn spring-boot:run</code></span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">5.</span>
                  <span>Access Swagger UI at <code className="rounded bg-background px-2 py-0.5">localhost:8080/swagger-ui.html</code></span>
                </li>
              </ol>
            </div>
            
            <div>
              <h4 className="mb-2 font-semibold text-primary">Frontend Setup</h4>
              <ol className="space-y-2 text-sm">
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">1.</span>
                  <span>Frontend is already running in this environment</span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">2.</span>
                  <span>For local dev: <code className="rounded bg-background px-2 py-0.5">npm install</code></span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">3.</span>
                  <span>Start dev server: <code className="rounded bg-background px-2 py-0.5">npm run dev</code></span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">4.</span>
                  <span>Configure API URL to point to backend (port 8080)</span>
                </li>
                <li className="flex gap-2">
                  <span className="font-mono text-muted-foreground">5.</span>
                  <span>Build UI components to consume REST APIs</span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </main>

      {/* Footer */}
      <footer className="mt-12 border-t border-border bg-card/50 py-8">
        <div className="container mx-auto px-6 text-center text-sm text-muted-foreground">
          <p>Spring Boot 3.2 + Next.js 15 • Full-Stack Project Management System</p>
          <p className="mt-2">Built with Java 17, TypeScript, PostgreSQL, and Tailwind CSS</p>
        </div>
      </footer>
    </div>
  );
}