# Documentación del Backend - ModelSecurity

## Arquitectura General

El backend de ModelSecurity es una aplicación REST API desarrollada con Spring Boot que implementa un sistema de seguridad basado en roles y permisos. La arquitectura sigue el patrón de diseño MVC (Model-View-Controller) con separación clara de responsabilidades:

- **Controladores (Controllers)**: Manejan las solicitudes HTTP y delegan la lógica de negocio a los servicios.
- **Servicios (Services)**: Contienen la lógica de negocio y coordinan las operaciones entre repositorios y otras capas.
- **Repositorios (Repositories)**: Gestionan el acceso a datos utilizando Spring Data JPA.
- **Entidades (Entities)**: Representan las tablas de la base de datos con anotaciones JPA.
- **DTOs (Data Transfer Objects)**: Objetos para transferencia de datos entre capas, evitando exponer entidades directamente.
- **Mappers**: Utilizan MapStruct para convertir entre entidades y DTOs.

La aplicación utiliza soft deletes (borrado lógico) en todas las entidades mediante el campo `is_deleted`, implementado con anotaciones de Hibernate `@SQLDelete` y `@SQLRestriction`.

## Tecnologías y Frameworks Utilizados

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.3.1**: Framework principal para desarrollo de aplicaciones Java.
- **Spring Web**: Para crear APIs REST.
- **Spring Data JPA**: Para acceso a datos y mapeo objeto-relacional.
- **Spring Security**: Para autenticación y autorización.
- **JWT (JSON Web Tokens)**: Para autenticación stateless.
- **Flyway**: Para migraciones de base de datos.
- **Swagger/OpenAPI**: Para documentación de APIs (SpringDoc).
- **Lombok**: Para reducir código boilerplate.
- **MapStruct**: Para mapeo entre entidades y DTOs.
- **Hibernate**: ORM subyacente.
- **Bases de datos soportadas**:
  - MySQL
  - PostgreSQL
  - SQL Server

## Estructura de Directorios

```
src/
├── main/
│   ├── java/com/modelsecurity/
│   │   ├── ModelsecurityApplication.java          # Clase principal de Spring Boot
│   │   ├── config/                                # Configuraciones de la aplicación
│   │   │   ├── DataInitializer.java              # Inicialización de datos
│   │   │   └── SwaggerConfig.java                # Configuración de Swagger
│   │   ├── controller/                            # Controladores REST
│   │   │   ├── AuthController.java               # Autenticación (login/register)
│   │   │   ├── UserController.java               # Gestión de usuarios
│   │   │   ├── PersonController.java             # Gestión de personas
│   │   │   ├── RolController.java                # Gestión de roles
│   │   │   ├── PermissionController.java         # Gestión de permisos
│   │   │   ├── FormController.java               # Gestión de formularios
│   │   │   ├── ModuleController.java             # Gestión de módulos
│   │   │   ├── FormModuleController.java         # Asociación formulario-módulo
│   │   │   ├── RolFormPermitController.java      # Permisos de rol sobre formularios
│   │   │   └── RolUserController.java            # Asociación rol-usuario
│   │   ├── dto/                                  # Data Transfer Objects
│   │   │   ├── auth/                             # DTOs de autenticación
│   │   │   └── [Entity]Dto.java                  # DTOs para cada entidad
│   │   ├── entity/                               # Entidades JPA
│   │   ├── exception/                            # Manejo de excepciones
│   │   │   ├── ApiError.java                     # Clase de error de API
│   │   │   └── GlobalExceptionHandler.java       # Manejador global de excepciones
│   │   ├── factory/                              # Factory pattern para servicios
│   │   ├── mapper/                               # Mapeadores MapStruct
│   │   ├── repository/                           # Repositorios JPA
│   │   ├── security/                             # Configuración de seguridad
│   │   │   ├── CustomUserDetailsService.java     # Servicio de detalles de usuario
│   │   │   ├── JwtAuthenticationFilter.java      # Filtro JWT
│   │   │   ├── JwtTokenProvider.java             # Proveedor de tokens JWT
│   │   │   ├── SecurityConfig.java               # Configuración de seguridad
│   │   │   └── UserSecurity.java                 # Utilidades de seguridad
│   │   └── service/                              # Servicios de negocio
│   │       ├── impl/                             # Implementaciones de servicios
│   │       └── interfaces/                       # Interfaces de servicios
│   └── resources/
│       ├── application.properties                # Configuración principal
│       ├── application-[db].properties           # Configuraciones por BD
│       ├── migration/                            # Scripts de migración Flyway
│       │   ├── mysql/
│       │   ├── postgresql/
│       │   └── sqlserver/
│       └── META-INF/                             # Metadatos adicionales
└── test/                                         # Tests
```

## Endpoints de API

### Autenticación (`/api/auth`)
- `POST /api/auth/register`: Registra un nuevo usuario. Crea persona, usuario y asigna rol USER por defecto.
- `POST /api/auth/login`: Autentica usuario y retorna token JWT.

### Usuarios (`/api/users`) - Requiere rol ADMIN
- `GET /api/users`: Lista todos los usuarios.
- `GET /api/users/{id}`: Obtiene usuario por ID.
- `POST /api/users`: Crea nuevo usuario.
- `PUT /api/users/{id}`: Actualiza usuario existente.
- `DELETE /api/users/{id}`: Elimina usuario (soft delete).

### Personas (`/api/persons`)
- `GET /api/persons/me`: Obtiene datos de la persona del usuario actual (roles USER, ADMIN).
- `GET /api/persons`: Lista todas las personas (ADMIN).
- `GET /api/persons/{id}`: Obtiene persona por ID (ADMIN o propietario).
- `POST /api/persons`: Crea nueva persona (ADMIN).
- `PUT /api/persons/{id}`: Actualiza persona (ADMIN o propietario).
- `DELETE /api/persons/{id}`: Elimina persona (ADMIN).

### Roles (`/api/roles`) - Requiere rol ADMIN
- `GET /api/roles`: Lista todos los roles.
- `GET /api/roles/{id}`: Obtiene rol por ID.
- `POST /api/roles`: Crea nuevo rol.
- `PUT /api/roles/{id}`: Actualiza rol existente.
- `DELETE /api/roles/{id}`: Elimina rol (soft delete).

### Permisos (`/api/permissions`) - Requiere rol ADMIN
- `GET /api/permissions`: Lista todos los permisos.
- `GET /api/permissions/{id}`: Obtiene permiso por ID.
- `POST /api/permissions`: Crea nuevo permiso.
- `PUT /api/permissions/{id}`: Actualiza permiso existente.
- `DELETE /api/permissions/{id}`: Elimina permiso (soft delete).

### Formularios (`/api/forms`) - Requiere roles ADMIN o USER
- `GET /api/forms`: Lista todos los formularios.
- `GET /api/forms/{id}`: Obtiene formulario por ID.
- `POST /api/forms`: Crea nuevo formulario (ADMIN).
- `PUT /api/forms/{id}`: Actualiza formulario (ADMIN).
- `DELETE /api/forms/{id}`: Elimina formulario (ADMIN).

### Módulos (`/api/modules`) - Requiere roles ADMIN o USER
- `GET /api/modules`: Lista todos los módulos.
- `GET /api/modules/{id}`: Obtiene módulo por ID.
- `POST /api/modules`: Crea nuevo módulo (ADMIN).
- `PUT /api/modules/{id}`: Actualiza módulo (ADMIN).
- `DELETE /api/modules/{id}`: Elimina módulo (ADMIN).

### Asociación Formulario-Módulo (`/api/form-modules`) - Requiere roles ADMIN o USER
- `GET /api/form-modules`: Lista todas las asociaciones.
- `GET /api/form-modules/{id}`: Obtiene asociación por ID.
- `POST /api/form-modules`: Crea nueva asociación (ADMIN).
- `DELETE /api/form-modules/{id}`: Elimina asociación (ADMIN).

### Permisos de Rol sobre Formularios (`/api/rol-form-permits`) - Requiere rol ADMIN
- `GET /api/rol-form-permits`: Lista todos los permisos de rol.
- `GET /api/rol-form-permits/{id}`: Obtiene permiso de rol por ID.
- `POST /api/rol-form-permits`: Crea nuevo permiso de rol.
- `DELETE /api/rol-form-permits/{id}`: Elimina permiso de rol.

### Asociación Rol-Usuario (`/api/rol-users`)
- `GET /api/rol-users`: Lista todas las asociaciones (ADMIN).
- `GET /api/rol-users/{id}`: Obtiene asociación por ID (ADMIN o propietario del rol).
- `POST /api/rol-users`: Crea nueva asociación (ADMIN).
- `DELETE /api/rol-users/{id}`: Elimina asociación (ADMIN).

## Modelos de Datos y Esquemas de Base de Datos

### Entidades Principales

#### Person
- `id`: Integer (PK)
- `firstName`: String (requerido)
- `lastName`: String (requerido)
- `documentType`: String
- `document`: String
- `dateBorn`: LocalDate
- `phoneNumber`: String
- `gender`: String
- `personExter`: String
- `epsId`: String
- `secondLastName`: String
- `middleName`: String
- `cityId`: Integer
- `isDeleted`: Boolean (default: false)

#### User
- `id`: Integer (PK)
- `email`: String (único, requerido)
- `password`: String (requerido, encriptado)
- `registrationDate`: LocalDateTime
- `isDeleted`: Boolean (default: false)
- `person`: Person (FK, opcional)

#### Rol
- `id`: Integer (PK)
- `name`: String
- `description`: String
- `isDeleted`: Boolean (default: false)

#### Permission
- `id`: Integer (PK)
- `name`: String
- `description`: String
- `isDeleted`: Boolean (default: false)

#### Form
- `id`: Integer (PK)
- `name`: String
- `description`: String
- `isDeleted`: Boolean (default: false)

#### Module
- `id`: Integer (PK)
- `name`: String
- `description`: String
- `isDeleted`: Boolean (default: false)

#### FormModule
- `id`: Integer (PK)
- `isDeleted`: Boolean (default: false)
- `form`: Form (FK, requerido)
- `module`: Module (FK, requerido)

#### RolUser
- `id`: Integer (PK)
- `isDeleted`: Boolean (default: false)
- `rol`: Rol (FK, requerido)
- `user`: User (FK, requerido)

#### RolFormPermit
- `id`: Integer (PK)
- `isDeleted`: Boolean (default: false)
- `rol`: Rol (FK, requerido)
- `form`: Form (FK, requerido)
- `permission`: Permission (FK, requerido)

### Esquema de Base de Datos (PostgreSQL)

```sql
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    document_type VARCHAR(50),
    document VARCHAR(50),
    date_born DATE,
    phone_number VARCHAR(20),
    gender VARCHAR(10),
    person_exter VARCHAR(255),
    eps_id VARCHAR(50),
    second_last_name VARCHAR(255),
    middle_name VARCHAR(255),
    city_id INTEGER,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    person_id INTEGER,
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE rol (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE permission (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE form (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE module (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE form_module (
    id SERIAL PRIMARY KEY,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    form_id INTEGER NOT NULL,
    module_id INTEGER NOT NULL,
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (module_id) REFERENCES module(id)
);

CREATE TABLE rol_user (
    id SERIAL PRIMARY KEY,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    rol_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE rol_form_permit (
    id SERIAL PRIMARY KEY,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    rol_id INTEGER NOT NULL,
    form_id INTEGER NOT NULL,
    permission_id INTEGER NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (permission_id) REFERENCES permission(id)
);
```

## Configuración de Entorno

### Archivo Principal (`application.properties`)
```properties
spring.application.name=modelsecurity
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Flyway
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

# JWT
jwt.secret=secret-key
jwt.expiration-ms=86400000

# Perfil por defecto
spring.profiles.active=postgresql
```

### Configuraciones por Base de Datos

#### MySQL (`application-mysql.properties`)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/modelsecurity
spring.datasource.username=user
spring.datasource.password=pass
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.flyway.locations=classpath:db/migration/mysql
```

#### PostgreSQL (`application-postgresql.properties`)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/modelsecurity
spring.datasource.username=user
spring.datasource.password=pass
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.flyway.locations=classpath:db/migration/postgresql
```

#### SQL Server (`application-sqlserver.properties`)
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=modelsecurity;encrypt=false
spring.datasource.username=sa
spring.datasource.password=Passw0rd!
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.flyway.locations=classpath:db/migration/sqlserver
```

## Manejo de Errores

El sistema utiliza un `GlobalExceptionHandler` con `@ControllerAdvice` para manejar excepciones globalmente:

- **MethodArgumentNotValidException**: Errores de validación de entrada (HTTP 400).
- **ConstraintViolationException**: Violaciones de restricciones (HTTP 400).
- **IllegalArgumentException**: Argumentos inválidos (HTTP 400).
- **RuntimeException**: Errores de ejecución (HTTP 500).

Todas las respuestas de error siguen el formato `ApiError`:
```json
{
  "timestamp": "2023-11-11T16:52:57.458Z",
  "statusCode": 400,
  "error": "Bad Request",
  "message": "Errores de validación",
  "path": "/api/users",
  "details": ["email: must not be blank", "password: size must be between 6 and 100"]
}
```

## Autenticación y Autorización

### Autenticación JWT
- **Login**: Envía email y password, retorna token JWT con expiración configurable.
- **Registro**: Crea usuario con rol USER por defecto.
- **Filtro JWT**: Valida tokens en cada request y establece contexto de seguridad.

### Autorización Basada en Roles
- **Roles**: USER, ADMIN (definidos en BD).
- **Permisos Granulares**: `PERM:FORM_NAME:PERMISSION_NAME` para permisos específicos sobre formularios.
- **Control de Acceso**: Anotaciones `@PreAuthorize` en controladores y métodos.

### Configuración de Seguridad
- **Stateless**: No mantiene sesiones del lado servidor.
- **CORS**: Deshabilitado para simplicidad.
- **Endpoints Públicos**: `/api/auth/**`, Swagger UI, documentación OpenAPI.
- **Encriptación de Passwords**: BCrypt.

### Servicio de Detalles de Usuario
- Carga usuario por email.
- Asigna authorities: `ROLE_[ROL_NAME]` y `PERM:[FORM]:[PERMISSION]`.

## Detalles Adicionales

### Patrón de Diseño
- **Factory Pattern**: Para creación de servicios (`ServiceFactory`).
- **Repository Pattern**: Abstracción de acceso a datos.
- **DTO Pattern**: Separación entre entidades de BD y objetos de transferencia.

### Migraciones de Base de Datos
- **Flyway**: Versionado de esquemas.
- **Multi-BD**: Scripts separados por motor de BD.
- **Soft Deletes**: Implementados a nivel de BD con restricciones SQL.

### Validaciones
- **Bean Validation**: Anotaciones `@Valid`, `@NotBlank`, etc.
- **Validación Manual**: En controladores para lógica de negocio específica.

### Documentación API
- **Swagger/OpenAPI 3**: Disponible en `/swagger-ui.html`.
- **Bearer Authentication**: Configurado para JWT.

### Inicialización de Datos
- **DataInitializer**: Ejecuta al inicio para poblar datos iniciales (roles, permisos, etc.).

### Consideraciones de Producción
- **Configuración Externa**: Variables de entorno para secrets y conexiones.
- **Logging**: Configurado para mostrar SQL ejecutado.
- **Transaccionalidad**: Métodos de servicio marcados como `@Transactional`.
- **Auditoría**: Campos de timestamp y usuario podrían agregarse en futuras versiones.

### Escalabilidad
- **Separación de Responsabilidades**: Capas bien definidas facilitan testing y mantenimiento.
- **Multi-BD**: Soporte nativo para diferentes motores.
- **JWT Stateless**: Fácil escalado horizontal.
- **Soft Deletes**: Preserva integridad referencial.

Esta documentación proporciona una visión completa del backend, permitiendo entender, mantener y escalar el sistema de manera efectiva.