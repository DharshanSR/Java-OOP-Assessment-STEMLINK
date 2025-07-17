#!/bin/bash

# Deployment script for Java OOP Assessment
# This script supports both local and CI/CD deployments

set -e  # Exit on any error

ENVIRONMENT=${1:-"local"}
IMAGE_TAG=${2:-"latest"}

echo "🚀 Starting deployment to $ENVIRONMENT environment..."
echo "Image tag: $IMAGE_TAG"
echo

# Build Docker image
echo "📦 Building Docker image..."
docker build -t java-oop-assessment:$IMAGE_TAG .
echo "✅ Docker image built successfully"
echo

# Apply Kubernetes manifests
echo "🎯 Deploying to Kubernetes..."
kubectl apply -f k8s-deployment.yaml
kubectl apply -f k8s-service.yaml
echo "✅ Kubernetes manifests applied"
echo

# Wait for deployment to be ready
echo "⏳ Waiting for deployment to be ready..."
kubectl rollout status deployment/java-oop-assessment --timeout=300s
echo "✅ Deployment is ready"
echo

# Show status
echo "📊 Deployment status:"
echo "--------------------"
kubectl get pods -l app=java-oop-assessment
echo
kubectl get svc java-oop-assessment-service
echo

echo "🎉 Deployment completed successfully!"
echo "Environment: $ENVIRONMENT"
echo "Image: java-oop-assessment:$IMAGE_TAG"
echo "Timestamp: $(date)"
