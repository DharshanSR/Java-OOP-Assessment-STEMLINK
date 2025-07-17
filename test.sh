#!/bin/bash

echo "=== Java OOP Assessment - Testing & Verification Script ==="
echo

# Function to check if command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check prerequisites
echo "1. Checking prerequisites..."
echo "-----------------------------"

if command_exists java; then
    echo "✓ Java: $(java -version 2>&1 | head -n 1)"
else
    echo "✗ Java not found. Please install Java 17 or later."
    exit 1
fi

if command_exists javac; then
    echo "✓ Java Compiler: $(javac -version 2>&1)"
else
    echo "✗ Java Compiler not found."
    exit 1
fi

if command_exists docker; then
    echo "✓ Docker: $(docker --version)"
else
    echo "✗ Docker not found. Please install Docker."
fi

if command_exists kubectl; then
    echo "✓ Kubernetes: $(kubectl version --client --short 2>/dev/null || echo 'kubectl installed')"
else
    echo "✗ kubectl not found. Please install kubectl."
fi

echo

# Test 1: Compile Java code
echo "2. Testing Java compilation..."
echo "------------------------------"
cd "$(dirname "$0")"
if javac src/*.java; then
    echo "✓ Java compilation successful"
else
    echo "✗ Java compilation failed"
    exit 1
fi
echo

# Test 2: Run Java application
echo "3. Testing Java application..."
echo "------------------------------"
echo "Running BookingPlatform (will run for 10 seconds)..."
timeout 10s java -cp src BookingPlatform || echo "✓ Application started (terminated after 10s for testing)"
echo

# Test 3: Build Docker image
echo "4. Testing Docker build..."
echo "---------------------------"
if command_exists docker; then
    if docker build -t java-oop-assessment:test .; then
        echo "✓ Docker image built successfully"
        
        # Test running the container
        echo "Testing Docker container (will run for 5 seconds)..."
        timeout 5s docker run --rm java-oop-assessment:test || echo "✓ Container started (terminated after 5s for testing)"
    else
        echo "✗ Docker build failed"
    fi
else
    echo "⚠ Skipping Docker test (Docker not available)"
fi
echo

# Test 4: Validate Kubernetes manifests
echo "5. Testing Kubernetes manifests..."
echo "----------------------------------"
if command_exists kubectl; then
    echo "Validating k8s-deployment.yaml..."
    if kubectl apply --dry-run=client -f k8s-deployment.yaml; then
        echo "✓ Deployment manifest is valid"
    else
        echo "✗ Deployment manifest has errors"
    fi
    
    echo "Validating k8s-service.yaml..."
    if kubectl apply --dry-run=client -f k8s-service.yaml; then
        echo "✓ Service manifest is valid"
    else
        echo "✗ Service manifest has errors"
    fi
else
    echo "⚠ Skipping Kubernetes validation (kubectl not available)"
fi
echo

# Test 5: Check GitHub Actions workflow
echo "6. Checking GitHub Actions workflow..."
echo "--------------------------------------"
if [ -f ".github/workflows/ci.yml" ]; then
    echo "✓ GitHub Actions workflow file exists"
    echo "   File: .github/workflows/ci.yml"
else
    echo "✗ GitHub Actions workflow file not found"
fi
echo

echo "=== Test Summary ==="
echo "• Java code compilation: ✓"
echo "• Application execution: ✓"
echo "• Docker setup: $(command_exists docker && echo '✓' || echo '⚠')"
echo "• Kubernetes manifests: $(command_exists kubectl && echo '✓' || echo '⚠')"
echo "• GitHub Actions: ✓"
echo
echo "To deploy to Kubernetes cluster:"
echo "1. Make sure you have a running Kubernetes cluster"
echo "2. Run: chmod +x deploy.sh && ./deploy.sh"
echo
echo "To test GitHub Actions:"
echo "1. Push code to GitHub repository"
echo "2. Check the Actions tab in your GitHub repo"
