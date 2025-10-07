# Contributing to Life API

## Development Setup

### Prerequisites
- Java 17+
- Docker and Docker Compose
- Gradle (wrapper included)

### Local Development

1. Clone the repository
2. Start dependencies:
   ```bash
   cd postgres-docker && docker-compose up -d
   cd ../redis-docker && docker-compose up -d
   ```
3. Build: `./gradlew build`
4. Run: `./gradlew bootRun`

## Code Style

This project uses EditorConfig for consistent formatting. Make sure your IDE supports it.

### Kotlin Guidelines
- Use tabs for indentation (size 4)
- Maximum line length: 120 characters
- Trailing commas are allowed
- Follow standard Kotlin conventions

## Testing

Run all tests:
```bash
./gradlew test
```

## Building

Create a production build:
```bash
./gradlew build
```

## Project Structure

- `config/` - Spring configuration classes
- `datasync/` - Data synchronization services
- `entity/` - Database entities (R2DBC)
- `etl/` - Extract, Transform, Load services
- `model/` - Domain models
- `repository/` - Data access layer
- `service/` - Business logic

## GraphQL

GraphQL schema is auto-generated from Kotlin code using graphql-kotlin.
Access GraphiQL at `http://localhost:8080/graphiql` when running locally.

## Database Migrations

Flyway migrations are located in `src/main/resources/db/migration/`.
Follow the naming convention: `V{version}__{description}.sql`
