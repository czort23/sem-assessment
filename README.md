# Software Engineering Methods
- Master Build Status ![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/czort23/sem-assessment/main.yml?branch=master)
- Develop Build Status ![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/czort23/sem-assessment/main.yml?branch=develop)
- License [![LICENSE](https://img.shields.io/github/license/czort23/sem-assessment.svg?style=flat-square)](https://github.com/czort23/sem-assessment/blob/master/LICENSE)
- Release [![Releases](https://img.shields.io/github/release/czort23/sem-assessment/all.svg?style=flat-square)](https://github.com/czort23/sem-assessment/releases)


## Deployment Steps (Simple Explanation)

### 1. CI Pipeline
Every push to `develop` runs:
- Unit tests (Maven)
- Integration tests using a temporary MySQL Docker container
- Docker image build for db, app, and web services

### 2. Automated Release
If all tests pass:
- Maven creates the runnable JAR (`sem-assessment.jar`)
- GitHub Actions uploads it as a release asset
- Release appears under: https://github.com/czort23/sem-assessment/releases

### 3. Run Locally
Use Docker Compose:
