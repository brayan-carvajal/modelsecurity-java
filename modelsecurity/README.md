# ModelSecurity

Sistema de autenticación y autorización con Spring Boot 3, JPA, JWT, Roles y Permisos por Formulario.

## Características
- Autenticación via JWT (login y registro)
- Roles y permisos asociados a formularios (granularidad PERM:FORM:PERMISSION)
- Soft delete con filtros globales (no se elimina físicamente)
- Validación de datos con Jakarta Validation
- Manejo global de errores estructurados (`ApiError`)
- Swagger/OpenAPI para documentación

## Endpoints principales
### Autenticación
- POST `/api/auth/register` (requiere `personId` existente)
- POST `/api/auth/login` -> retorna `token` Bearer

### Ejemplo de Authorization header
```
Authorization: Bearer <TOKEN>
```

## Roles y Permisos
Los roles se transforman en authorities `ROLE_<NOMBRE>`.
Los permisos por formulario generan authorities `PERM:<FORM_NAME>:<PERMISSION_NAME>`.
Puedes usar `@PreAuthorize("hasAuthority('PERM:USER_FORM:READ')")` en métodos.

## Soft Delete
Las entidades usan `@SQLDelete` y `@SQLRestriction` para marcar `is_deleted=true` y excluir registros en consultas.

## Building
Windows PowerShell:
```powershell
mvnw clean package
```

## Pruebas
```powershell
mvnw test
```

## Variables importantes
- `jwt.secret` (debe ser largo, recomendado base64)
- `jwt.expiration-ms` (milisegundos)

## Próximos pasos sugeridos
- Refresh tokens
- Auditoría de acciones de seguridad
- Rate limiting
- Más tests (permisos granulares)

