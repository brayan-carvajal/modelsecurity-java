# 🧠 Contexto General para Copilot Agent — API REST Multi-Base de Datos (Spring Boot)

## 🧩 CONTEXTO GENERAL DEL PROYECTO

Estoy desarrollando una **API REST en Java con Spring Boot 3**, que debe conectarse **dinámicamente a tres bases de datos distintas**:  
- **MySQL**  
- **PostgreSQL**  
- **SQL Server**

El objetivo es que **una sola instancia del backend** pueda conectarse a cualquiera de estas bases **en tiempo de ejecución**, sin reiniciar la aplicación, y que las peticiones decidan la base de datos **según un header HTTP o token JWT**.

---

## ⚙️ DETALLES TÉCNICOS DEL MODELO DE SEGURIDAD Y CONEXIÓN

### 1. Arquitectura:
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- Spring Security con JWT
- Swagger (OpenAPI) para pruebas interactivas

### 2. Flujo de conexión esperado:
- Al iniciar la app, se cargan las tres conexiones (`DataSource`): MySQL, PostgreSQL y SQL Server.
- La aplicación arranca usando **MySQL como base de datos por defecto**.
- Cuando el usuario hace peticiones desde Swagger o un cliente HTTP:
  - Si no envía ningún header, se usa la base por defecto (MySQL).
  - Si envía el header `X-DB` (con valor `mysql`, `postgres` o `sqlserver`), el backend enruta dinámicamente la conexión hacia esa base de datos.
- La base activa cambia **por request**, no globalmente.

### 3. Gestión de contexto de base de datos:
- Se implementa un `TenantContext` con `ThreadLocal<String>` para almacenar el tenant actual.
- Un `Filter` (por ejemplo `TenantFilter`) lee el header `X-DB` en cada request y actualiza el contexto.
- Se crea una clase que extiende `AbstractRoutingDataSource` (`MultiTenantDataSource`) para decidir a qué conexión apuntar según el contexto.

### 4. Configuración de Swagger/OpenAPI:
- Swagger debe incluir un campo de autorización adicional (API Key) llamado `X-DB` en el botón **Authorize**, para poder probar cada base.
- Ejemplo:
  - `X-DB: mysql`
  - `X-DB: postgres`
  - `X-DB: sqlserver`

### 5. Seguridad y JWT:
- Los tokens JWT deben seguir funcionando independientemente del tenant.
- Alternativamente, el “tenant” podría extraerse del claim del JWT en vez del header `X-DB`.

### 6. Requisitos de datos:
- Las tres bases de datos deben tener **el mismo esquema de tablas y entidades**.
- El esquema de datos se gestiona con **JPA** y opcionalmente **Flyway** o **Liquibase**.

### 7. Objetivo de comportamiento:
- El backend inicia conectado a MySQL (por defecto).
- Desde Swagger se puede cambiar la base dinámica colocando el valor en `X-DB`.
- Todas las operaciones CRUD se ejecutan en la base seleccionada.
- No se requiere reiniciar la app para cambiar de base.
- El mismo código de repositorios, servicios y controladores funciona para todas.

---

## 💾 Configuración esperada (application.yml)

```yaml
spring:
  datasource:
    mysql:
      url: jdbc:mysql://localhost:3306/tu_base_mysql
      username: root
      password: root
      driver-class-name: com.mysql.cj.jdbc.Driver

    postgres:
      url: jdbc:postgresql://localhost:5432/tu_base_postgres
      username: postgres
      password: admin
      driver-class-name: org.postgresql.Driver

    sqlserver:
      url: jdbc:sqlserver://localhost;databaseName=tu_base_sqlserver
      username: sa
      password: admin123
      driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver

  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
```

---

## 🧩 Clases que Copilot debe generar o completar

1. `TenantContext.java`  
   → Clase estática con `ThreadLocal<String>` para guardar el tenant actual.

2. `MultiTenantDataSource.java`  
   → Extiende `AbstractRoutingDataSource` y decide el `DataSource` según `TenantContext.getTenant()`.

3. `DataSourceConfig.java`  
   → Declara los tres `DataSource` (`mysql`, `postgres`, `sqlserver`), los registra en un `Map`, y crea un bean `@Primary` de tipo `MultiTenantDataSource`.

4. `TenantFilter.java`  
   → Filtro que lee el header `X-DB` y actualiza el `TenantContext`.  
   → Se limpia el contexto al final del request (`finally`).

5. `SwaggerConfig.java`  
   → Configura un esquema de seguridad tipo `APIKEY` para incluir el header `X-DB` en el botón **Authorize**.

6. `TestController.java`  
   → Endpoint GET `/db/actual` que retorne un texto como:  
   ```json
   { "tenant": "postgres", "status": "connected" }
   ```
   para validar qué base se está usando.

---

## 🔐 Seguridad y buenas prácticas que debe respetar

- Validar que el valor de `X-DB` sea solo uno de los permitidos (`mysql`, `postgres`, `sqlserver`).
- Manejar pools de conexiones independientes por cada base.
- Mantener transacciones (`@Transactional`) funcionando correctamente.
- Mantener compatibilidad con Spring Security y JWT.
- No reiniciar el contexto global al cambiar de base.
- Cerrar conexiones inactivas de forma automática con HikariCP.

---

## 🧩 Objetivo final

Con este contexto, Copilot debe ser capaz de:
- generar las clases necesarias,
- autocompletar configuraciones,
- crear controladores y servicios,
- y permitir probar en Swagger la API con cualquiera de las tres bases solo cambiando `X-DB`.

