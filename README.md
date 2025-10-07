# Life API

A reactive personal data aggregation and metrics tracking API built with Kotlin and Spring Boot.

## Overview

Life API is a GraphQL-based service that aggregates and tracks personal metrics from various data sources. It provides a unified interface for querying life data including inbox counts, shopping metrics, and other personal information.

## Tech Stack

- **Kotlin** - Primary language
- **Spring Boot 3.3.2** - Application framework
- **Spring WebFlux** - Reactive web framework
- **GraphQL** - API query language (ExpediaGroup graphql-kotlin)
- **PostgreSQL** - Primary database (R2DBC for reactive access)
- **Redis** - Caching layer
- **gRPC** - Inter-service communication
- **Flyway** - Database migrations
- **Prometheus** - Metrics and monitoring

## Prerequisites

- Java 17+
- PostgreSQL
- Redis
- Gradle (wrapper included)

## Getting Started

### Local Development

1. **Start PostgreSQL and Redis**:
   ```bash
   # PostgreSQL
   cd postgres-docker && docker-compose up -d

   # Redis
   cd redis-docker && docker-compose up -d
   ```

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```

4. **Access GraphQL Playground**:
   - GraphQL endpoint: `http://localhost:8080/graphql`
   - GraphiQL UI: `http://localhost:8080/graphiql`

5. **Metrics**:
   - Prometheus metrics: `http://localhost:8080/actuator/prometheus`
   - Health check: `http://localhost:8080/actuator/health`

## Project Structure

```
src/main/kotlin/com/example/life/
├── config/          # Application configuration
├── datasync/        # Data synchronization services
├── entity/          # Database entities
├── etl/             # Extract, Transform, Load operations
├── model/           # Domain models
├── repository/      # Data access layer
└── service/         # Business logic
```

## Features

- **Reactive Architecture** - Built with Kotlin coroutines and WebFlux for non-blocking I/O
- **GraphQL API** - Flexible querying with GraphQL
- **Scheduled Data Sync** - Automatic data synchronization from external sources
- **Metrics Collection** - Track inbox counts, shopping data, and custom metrics
- **Redis Caching** - Fast data access with Redis
- **Monitoring** - Prometheus metrics and Spring Actuator endpoints

## Deployment

The application can be deployed using the included `Procfile` or Kubernetes configurations in the `k8s/` directory.

## Testing

Run tests with:
```bash
./gradlew test
```

## License

MIT License - see [LICENSE](LICENSE) file for details
