import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.BACKEND_URL || "http://localhost:8080";

export async function GET(request: NextRequest) {
  try {
    const token = request.headers.get("Authorization");
    const orgId = request.headers.get("X-Organization-Id");
    
    const response = await fetch(`${BACKEND_URL}/api/projects`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        ...(token && { Authorization: token }),
        ...(orgId && { "X-Organization-Id": orgId }),
      },
    });

    const data = await response.json();
    
    return NextResponse.json(data, { status: response.status });
  } catch (error) {
    return NextResponse.json(
      { message: "Backend not available. Start Spring Boot on port 8080" },
      { status: 503 }
    );
  }
}

export async function POST(request: NextRequest) {
  try {
    const token = request.headers.get("Authorization");
    const orgId = request.headers.get("X-Organization-Id");
    const body = await request.json();
    
    const response = await fetch(`${BACKEND_URL}/api/projects`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        ...(token && { Authorization: token }),
        ...(orgId && { "X-Organization-Id": orgId }),
      },
      body: JSON.stringify(body),
    });

    const data = await response.json();
    
    return NextResponse.json(data, { status: response.status });
  } catch (error) {
    return NextResponse.json(
      { message: "Backend not available. Start Spring Boot on port 8080" },
      { status: 503 }
    );
  }
}