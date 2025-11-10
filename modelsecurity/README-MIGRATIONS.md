# Database Migrations with Flyway

This document provides comprehensive instructions for managing database migrations across PostgreSQL, MySQL, and SQL Server using Flyway.

## Overview

The application supports three database systems:
- **PostgreSQL** (default profile)
- **MySQL**
- **SQL Server**

Each database has its own migration directory with SQL scripts tailored to the specific SQL dialect.

## Directory Structure

```
src/main/resources/db/migration/
├── mysql/
│   ├── V1__Initial_schema.sql
│   └── V1_1__Add_rollback_support.sql
├── postgresql/
│   ├── V1__Initial_schema.sql
│   └── V1_1__Add_rollback_support.sql
└── sqlserver/
    ├── V1__Initial_schema.sql
    └── V1_1__Add_rollback_support.sql
```

## Running Migrations

### Prerequisites

1. **Start the databases** using Docker Compose:
   ```bash
   docker-compose up -d
   ```

2. **Verify database connectivity**:
   - PostgreSQL: localhost:5432
   - MySQL: localhost:3306
   - SQL Server: localhost:1433

### Running the Application

The migrations are automatically executed when the Spring Boot application starts.

#### For PostgreSQL (default):
```bash
./mvnw spring-boot:run
```

#### For MySQL:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

#### For SQL Server:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=sqlserver
```

### Manual Migration Commands

You can also run migrations manually using Maven:

```bash
# PostgreSQL
./mvnw flyway:migrate -Dspring-boot.run.profiles=postgresql

# MySQL
./mvnw flyway:migrate -Dspring-boot.run.profiles=mysql

# SQL Server
./mvnw flyway:migrate -Dspring-boot.run.profiles=sqlserver
```

## Migration Scripts

### Naming Convention

Flyway uses a specific naming convention for migration files:
- `V{version}__{description}.sql`
- Version numbers should be incremental (e.g., V1, V2, V1_1, V2_1)
- Description should be descriptive and use underscores instead of spaces

### Database-Specific Considerations

#### PostgreSQL
- Uses `SERIAL` for auto-incrementing IDs
- Reserved keywords like `user` must be quoted with double quotes
- Supports `TIMESTAMP` for datetime fields

#### MySQL
- Uses `AUTO_INCREMENT` for auto-incrementing IDs
- Uses `DATETIME` for datetime fields
- Uses `TINYINT(1)` or `BOOLEAN` for boolean fields

#### SQL Server
- Uses `IDENTITY(1,1)` for auto-incrementing IDs
- Uses `NVARCHAR` for Unicode text fields
- Uses `NTEXT` for large text fields
- Reserved keywords like `user` must be quoted with square brackets
- Uses `BIT` for boolean fields

## Error Handling and Rollbacks

### Automatic Error Handling

Flyway automatically handles errors by:
- Rolling back transactions on failure (for transactional databases)
- Preventing execution of failed migrations
- Maintaining migration history in `flyway_schema_history` table

### Manual Rollbacks

Flyway doesn't support automatic rollbacks, but you can create undo scripts:

1. Create a new migration file with the rollback logic
2. The rollback scripts in `V1_1__Add_rollback_support.sql` serve as examples

### Recovery from Failed Migrations

If a migration fails:

1. **Fix the migration script**
2. **Delete the failed entry** from `flyway_schema_history` table (if necessary)
3. **Restart the application** or run migrations again

## Flyway Configuration

### Global Settings (application.properties)
```properties
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
```

### Profile-Specific Settings
Each profile configures the appropriate migration locations:
- MySQL: `classpath:db/migration/mysql`
- PostgreSQL: `classpath:db/migration/postgresql`
- SQL Server: `classpath:db/migration/sqlserver`

## Best Practices

1. **Test migrations** on all database types before deploying
2. **Use transactions** appropriately (Flyway wraps each migration in a transaction)
3. **Version migrations** sequentially
4. **Document changes** in migration descriptions
5. **Backup databases** before running migrations in production
6. **Test rollbacks** in development environments

## Troubleshooting

### Common Issues

1. **Migration checksum mismatch**
   - Solution: Don't modify existing migration files. Create new ones instead.

2. **Database connection issues**
   - Verify Docker containers are running
   - Check connection credentials in application properties

3. **SQL syntax errors**
   - Ensure SQL is compatible with the target database dialect
   - Test scripts manually against the database

### Checking Migration Status

View applied migrations:
```sql
SELECT * FROM flyway_schema_history ORDER BY installed_rank;
```

### Cleaning Database (Development Only)

**⚠️ WARNING: This deletes all data and schema!**
```bash
./mvnw flyway:clean -Dspring-boot.run.profiles={profile}
```

## Version Control

- Commit migration files with application code
- Tag releases that include database schema changes
- Document schema changes in release notes

## Production Deployment

1. **Backup production databases**
2. **Test migrations** on staging environments first
3. **Deploy application** (migrations run automatically on startup)
4. **Verify migration success** by checking logs and schema history table
5. **Monitor application** for any issues post-migration