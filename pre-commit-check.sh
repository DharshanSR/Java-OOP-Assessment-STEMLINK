#!/bin/bash

echo "=== Pre-Commit Checklist ==="
echo

# Check git status
echo "1. Checking git status..."
echo "-------------------------"
git status --porcelain
echo

# Check if there are any uncommitted changes
if [ -z "$(git status --porcelain)" ]; then
    echo "ℹ️  No changes to commit"
else
    echo "📝 Found changes to commit"
fi
echo

# Clean and recompile Java code
echo "2. Cleaning and recompiling Java code..."
echo "----------------------------------------"
rm -f src/*.class
if javac src/*.java; then
    echo "✅ Java compilation successful"
else
    echo "❌ Java compilation failed - FIX BEFORE COMMITTING"
    exit 1
fi
echo

# Validate file structure
echo "3. Validating project structure..."
echo "----------------------------------"
required_files=(
    "src/BookingPlatform.java"
    "Dockerfile"
    "k8s-deployment.yaml"
    "k8s-service.yaml"
    "deploy.sh"
    ".github/workflows/ci.yml"
)

for file in "${required_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file exists"
    else
        echo "❌ $file missing"
    fi
done
echo

# Check for common issues
echo "4. Checking for common issues..."
echo "--------------------------------"

# Check for .class files (shouldn't be committed)
if find . -name "*.class" | grep -q .; then
    echo "⚠️  Found .class files - these should not be committed"
    echo "   Run: git rm --cached src/*.class (if already tracked)"
    echo "   Or add *.class to .gitignore"
else
    echo "✅ No .class files found"
fi

# Check if .gitignore exists
if [ -f ".gitignore" ]; then
    echo "✅ .gitignore exists"
else
    echo "⚠️  No .gitignore found - recommended to create one"
fi
echo

# Show what will be committed
echo "5. Files to be committed:"
echo "-------------------------"
git diff --cached --name-only
git ls-files --others --exclude-standard
echo

echo "=== Pre-Commit Summary ==="
echo "Before you run 'git add' and 'git commit':"
echo
echo "✅ RECOMMENDED STEPS:"
echo "1. git add .gitignore (if you created one)"
echo "2. git add ."
echo "3. git commit -m 'Add DevOps setup: Docker, Kubernetes, GitHub Actions'"
echo "4. git push origin main"
echo
echo "📋 WHAT WILL HAPPEN AFTER PUSH:"
echo "• GitHub Actions will automatically run CI/CD"
echo "• Your code will be built and tested"
echo "• Docker image can be built from the Dockerfile"
echo "• Kubernetes manifests are ready for deployment"
echo
echo "🔧 OPTIONAL: Create .gitignore if not exists:"
echo "cat > .gitignore << 'EOF'"
echo "*.class"
echo ".DS_Store"
echo "*.log"
echo "target/"
echo "EOF"
