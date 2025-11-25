# Software Engineering Methods
- Master Build Status ![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/czort23/sem-assessment/main.yml?branch=master)
- Develop Build Status ![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/czort23/sem-assessment/main.yml?branch=develop)
- License [![LICENSE](https://img.shields.io/github/license/czort23/sem-assessment.svg?style=flat-square)](https://github.com/czort23/sem-assessment/blob/master/LICENSE)
- Release [![Releases](https://img.shields.io/github/release/czort23/sem-assessment/all.svg?style=flat-square)](https://github.com/czort23/sem-assessment/releases)



## Deployment Steps

Our project uses GitHub Actions to automatically test, build, and release the application.

### 1. CI Pipeline (Automatically on push to `develop`)
- **Unit tests** run using Maven.
- **Integration tests** run using a temporary MySQL Docker container.
- **Docker images** for the app, database, and web server are built using `docker compose build`.

### 2. Automated Release
After all tests pass:
- Maven builds a single runnable JAR (`sem-assessment.jar`).
- GitHub Actions uploads the JAR as a **release asset**.
- A new release is created automatically under:  
  https://github.com/czort23/sem-assessment/releases

### 3. Run the project locally
You can run the full system using:

```bash
docker compose up
