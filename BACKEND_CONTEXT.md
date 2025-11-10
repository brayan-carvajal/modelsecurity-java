# Contexto del backend — Proyecto ModelSecurity (Spring Boot)

## Resumen rápido
Proyecto: ModelSecurity — sistema de autenticación, roles y permisos.
Lenguaje: Java 17
Framework: Spring Boot 3.5.7
Propósito: API REST para gestión de usuarios, roles, permisos y formularios, con autenticación JWT y persistencia por JPA (MySQL).

## Dependencias y stack clave
- Spring Boot Starter Web
- Spring Data JPA (Hibernate)
- Spring Security
- JJWT (io.jsonwebtoken 0.11.5)
- MySQL (conector)
- Lombok (provided)
- springdoc-openapi (Swagger UI)
- Java 17
- Maven (wrapper `mvnw` / `mvnw.cmd`)

Versión en `pom.xml`: parent Spring Boot 3.5.7

## Estructura importante del proyecto
Código fuente principal: `modelsecurity/src/main/java/com/modelsecurity`

Paquetes y archivos clave:
- `ModelsecurityApplication.java` — clase principal Spring Boot.
- `config/`
  - `DataInitializer.java` — inicializa datos (roles, permisos, etc.).
  - `SwaggerConfig.java` — configuración OpenAPI/Swagger.
- `controller/` — controladores REST (ej.: `AuthController`, `UserController`, `RolController`, `FormController`, etc.).
- `dto/` — objetos de transferencia (DTOs) para requests/responses.
- `entity/` — entidades JPA (`User`, `Person`, `Rol`, `Permission`, `Form`, `FormModule`, etc.).
- `repository/` — interfaces JPA (repositorios).
- `mapper/` — mapeo entre entidades y DTOs.
- `service/` y `service/impl/` — servicios de negocio e implementaciones.
- `security/`
  - `SecurityConfig.java` — configuración de seguridad (filtros, reglas de autorización).
  - `JwtTokenProvider.java` — generación y validación de tokens JWT.
  - `JwtAuthenticationFilter.java` — filtro JWT.
  - `CustomUserDetailsService.java` — carga de usuario para Spring Security.

Recursos: `modelsecurity/src/main/resources/application.properties` (propiedades de la aplicación)

Artefactos empaquetados: `target/classes/...` (clases compiladas y archivos generados)

## Archivos de configuración relevantes
- `pom.xml` — dependencias y plugins (Maven). Ya incluye `maven-compiler-plugin` configurado con `release 17` y Lombok como processor.
- `src/main/resources/application.properties` — configuración por defecto (DB, JWT, etc.). También existe `src/test/resources/application-test.properties` para pruebas.

Claves típicas a revisar/definir en `application.properties` / variables de entorno:
- spring.datasource.url=jdbc:mysql://<host>:<port>/<db>
- spring.datasource.username=
- spring.datasource.password=
- spring.jpa.hibernate.ddl-auto= (e.g., update / validate)
- app.jwt.secret= (secreto para firmar JWT)
- app.jwt.expiration-ms= (tiempo de expiración en ms)

> Nota: Revisa el archivo `application.properties` en `target/classes` si buscas valores empaquetados o los defaults usados en ejecución local.

## Cómo ejecutar localmente (Windows / PowerShell)
Recomendación para desarrollo con el wrapper de Maven incluido:

- Compilar y ejecutar la aplicación:

```powershell
# Compilar
.\mvnw.cmd -DskipTests package

# Ejecutar la app (dev, recarga por DevTools)
.\mvnw.cmd spring-boot:run
```

- Ejecutar tests:

```powershell
.\mvnw.cmd test
```

- Alternativa: empaquetar jar y ejecutar

```powershell
.\mvnw.cmd -DskipTests package
java -jar target\modelsecurity-0.0.1-SNAPSHOT.jar
```

## Endpoints principales (inventario rápido)
Los controladores en `controller/` exponen las rutas principales — ejemplos:
- `AuthController` — login, register, refresh token.
- `UserController` — CRUD usuarios.
- `RolController` — gestión de roles.
- `PermissionController` — gestión de permisos.
- `FormController` / `FormModuleController` — gestión de formularios y módulos.

Para ver la documentación runtime, usa Swagger UI: si la app corre en http://localhost:8080, el path por defecto de springdoc suele ser `http://localhost:8080/swagger-ui.html` o `/swagger-ui/index.html`.

## Puntos técnicos clave y contratos
- Entrada: la API espera JSON en la mayoría de endpoints REST; DTOs en `dto/` definen la forma exacta.
- Autenticación: JWT en header `Authorization: Bearer <token>`.
- Errores: se maneja `ApiError` y `GlobalExceptionHandler` para responses normalizadas.

Edge cases a tener en cuenta:
- Usuarios duplicados al registrar.
- Tokens expirados o firmados con secreto distinto.
- Relaciones JPA (Lazy vs Eager) que pueden provocar LazyInitializationException en serialización.
- Datos de inicialización vs ambiente de producción (no usar datos de ejemplo en prod).

## Tests y entorno de CI
- Tests unitarios/integración con `spring-boot-starter-test` y `spring-security-test`.
- En `pom.xml` existe dependencia `h2` para pruebas en memoria (scope test).

## Notas sobre dependencias y seguridad
- JJWT 0.11.5 está presente; verificar práctica de almacenamiento del `app.jwt.secret` en variables de entorno o vault en producción.
- Lombok está como `provided` — en IDEs asegúrate de tener el plugin de Lombok activado.

## Sugerencias de próximos pasos (para continuar conversando con ChatGPT y desarrollar)
- Confirmar las variables de entorno (DB, JWT secret) que usarás localmente.
- Proveer fragmentos de `application.properties` si quieres que adapte la configuración.
- Pedir: "Implementar endpoint X", "Agregar validaciones", "Escribir tests para Y" o "Refactorizar Z".

### Tareas sugeridas inmediatas
- Añadir README de desarrollo con pasos para levantar BD MySQL local (ej. docker-compose).
- Añadir script `init` para crear roles/permissions si no existe (DataInitializer ya ayuda, revisarlo).
- Escribir 2-3 tests de integración para `AuthController` (login/register).

## Información útil para un próximo turn con ChatGPT
Al pedirme continuar, puedes copiar/pegar esta plantilla mínima para contexto:

- Ruta del repo: `c:\Users\Aprendiz\Downloads\modelsecurity-java`
- Comando preferido para ejecutar: `.\mvnw.cmd spring-boot:run` (PowerShell)
- Archivos a revisar: `src/main/java/com/modelsecurity/security/JwtTokenProvider.java`, `SecurityConfig.java`, `controller/AuthController.java`.
- Qué quieres lograr: (ej. "corregir login", "agregar refresh token", "agregar rol admin predeterminado").

---

Si quieres, ahora puedo:
- Extraer y listar las propiedades actuales en `src/main/resources/application.properties`.
- Generar un `docker-compose.yml` mínimo con MySQL para arrancar la BD local.
- Crear tests iniciales para `AuthController`.

Indica qué prefieres y sigo con los cambios.
