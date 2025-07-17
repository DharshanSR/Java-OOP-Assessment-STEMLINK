#!/bin/bash
# Build Docker image
docker build -t java-oop-assessment:latest .
# Apply Kubernetes manifests
kubectl apply -f k8s-deployment.yaml
kubectl apply -f k8s-service.yaml
# Show status
kubectl get pods
kubectl get svc
