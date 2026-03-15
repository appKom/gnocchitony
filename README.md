# Autobank - Receipt Management System

Autobank is a Kotlin Spring Boot application designed for managing financial receipts and economic requests for committees at NTNU Online. The system provides a comprehensive solution for receipt submission, review processes, and administrative functions with secure OAuth2/JWT authentication.

## 🚀 Quick Start

Use one of these three modes depending on what you want to test.

### Copy-Paste Cheatsheet

```bash
# Local backend (no token required)
./gradlew bootRun

# Docker backend + database
docker compose up -d --build

# Docker backend + database with live reload
docker compose --profile dev up -d dev-database backend-dev

# Test production auth mode locally (token required)
./gradlew bootRun --args='--environment=prod'

# Stop Docker services
docker compose down
docker compose --profile dev down
```

### 1. Local Development (fastest feedback)

```bash
./gradlew bootRun
```

What happens:

- Runs backend on port 8080 from your machine.
- Uses `application-local.properties`.
- `environment=dev` disables JWT auth, so you can call APIs without a token.

Useful URLs:

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### 2. Docker Development (backend + database)

```bash
docker compose up -d --build
```

What happens:

- Starts MySQL and backend in containers.
- Backend is on `http://localhost:8080` (or `APP_PORT` from `.env`).
- MySQL is internal to Docker network (not published on host).
- Uses local profile (`SPRING_PROFILES_ACTIVE=local`), so `environment=dev` and no JWT required.

Stop:

```bash
docker compose down
```

### 3. Docker Development with Live Reload (Kotlin changes)

```bash
docker compose --profile dev up -d dev-database backend-dev
```

What happens:

- Runs backend via Gradle `bootRun --continuous` inside container.
- Mounts your project into the container.
- Code changes trigger rebuild/restart automatically.
- Backend is exposed on `http://localhost:8081` by default (`APP_DEV_PORT`).

Stop:

```bash
docker compose --profile dev down
```

### 4. Test Production Auth Locally (require token)

If you want to test real JWT/Auth0 behavior, run with `environment=prod`.

Local JVM:

```bash
./gradlew bootRun --args='--environment=prod'
```

Docker:

```bash
ENVIRONMENT=prod docker compose up -d --build
```

What changes in prod mode:

- All protected endpoints require Bearer token.
- Auth0 issuer/audience validation is enforced.

## 📋 Features

- **Receipt Management**: Create, view, and manage financial receipts
- **Admin Review System**: Approve or deny receipts with comments
- **Committee Integration**: Associate receipts with specific committees
- **File Attachments**: Upload and manage receipt attachments via Azure Blob Storage
- **Economic Requests**: Submit and manage economic requests (partial implementation)
- **Role-based Access Control**: Admin and regular user permissions
- **OAuth2 Authentication**: Secure authentication via Auth0

## 🏗️ Architecture

- **Backend**: Kotlin + Spring Boot
- **Database**: Microsoft SQL Server (Azure)
- **Authentication**: OAuth2/JWT via Auth0
- **File Storage**: Azure Blob Storage
- **Build Tool**: Gradle

## 📚 Documentation

Detailed documentation is available in the `/docs` folder:

- [API Routes](docs/api-routes.md) - Complete API endpoint documentation
- [Database Schema](docs/database-schema.md) - Database structure and relationships
- [Architecture](docs/architecture.md) - System architecture and design patterns
- [Setup & Deployment](docs/setup-deployment.md) - Detailed setup and deployment guide

## 🔧 Configuration

## 🔐 Authentication

All API endpoints require authentication via Bearer token:

```
Authorization: Bearer <access_token>
```

Authentication mode is controlled by `environment`:

- `environment=prod`: JWT/Auth0 is required.
- Any non-prod value (for example `environment=dev`): token auth is disabled for easier local development.

## 🛠️ Development

### Project Structure

```
src/main/kotlin/com/example/autobank/
├── controller/          # REST controllers
├── service/            # Business logic
├── repository/         # Data access layer
├── data/              # DTOs and data models
├── security/          # Security configuration
└── AutobankApplication.kt
```

### Key Endpoints

- `GET /api/auth/getuser` - Get current user info
- `POST /api/receipt/create` - Create new receipt
- `GET /api/receipt/getall` - List user receipts
- `GET /api/admin/receipt/all` - Admin: List all receipts
- `POST /api/admin/receipt/review` - Admin: Review receipt

For complete API documentation, see [docs/api-routes.md](docs/api-routes.md).

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is part of NTNU Online's application suite.
