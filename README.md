# Java OOP Assessment - STEMLINK

## How to Verify Everything is Working

### 1. Quick Test
Run the comprehensive test script:
```bash
./test.sh
```

### 2. Manual Testing Steps

#### Test Java Application
```bash
# Compile the code
javac src/*.java

# Run the application
java -cp src BookingPlatform
```

#### Test Docker
```bash
# Build the Docker image
docker build -t java-oop-assessment:latest .

# Run the container
docker run --rm java-oop-assessment:latest
```

#### Test Kubernetes (requires a running cluster)
```bash
# Deploy to Kubernetes
./deploy.sh

# Check if pods are running
kubectl get pods

# Check if service is created
kubectl get svc

# Clean up
kubectl delete -f k8s-deployment.yaml
kubectl delete -f k8s-service.yaml
```

#### Test GitHub Actions
1. Push your code to GitHub
2. Go to the "Actions" tab in your GitHub repository
3. You should see the CI workflow running automatically

### 3. Troubleshooting

**Java Version Issues:**
- Make sure you're using Java 21 (the code was compiled with javac 21)
- Update your Java installation if needed

**Docker Issues:**
- Make sure Docker Desktop is running
- Check if you can run: `docker --version`

**Kubernetes Issues:**
- Make sure you have a Kubernetes cluster running (Docker Desktop, minikube, etc.)
- Check cluster connection: `kubectl cluster-info`

**GitHub Actions Issues:**
- Make sure the `.github/workflows/ci.yml` file is in your repository
- Check that your repository has Actions enabled

### 4. What Each Component Does

- **GitHub Actions (`.github/workflows/ci.yml`)**: Automatically builds and tests your code when you push to GitHub
- **Docker (`Dockerfile`)**: Packages your application into a container
- **Kubernetes (`k8s-deployment.yaml`, `k8s-service.yaml`)**: Deploys your containerized app to a Kubernetes cluster
- **Scripts (`deploy.sh`, `test.sh`)**: Automate deployment and testing processes

### 5. Expected Outputs

When everything is working:
- ✅ Java compilation succeeds
- ✅ Application runs without errors
- ✅ Docker image builds successfully
- ✅ Kubernetes manifests are valid
- ✅ GitHub Actions workflow exists and runs on push
