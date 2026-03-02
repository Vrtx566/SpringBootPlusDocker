# Las Cariñosas - Crime Investigation System

Sistema de gestión de investigaciones criminales con Spring Boot, PostgreSQL, Keycloak y Docker.

## 🏗️ Arquitectura

- **Backend**: Spring Boot 3.x + Spring Security OAuth2 Resource Server
- **Frontend**: HTML/CSS/JS vanilla con Keycloak JS Adapter
- **Base de datos**: PostgreSQL 15
- **Autenticación**: Keycloak 23.0 (OAuth2/OIDC)
- **Contenedores**: Docker Compose

## 🚀 Inicio Rápido

### Prerrequisitos
- Docker y Docker Compose instalados
- Puerto 3000 (Frontend), 8080 (Backend), 8180 (Keycloak), 5432 (PostgreSQL) disponibles

### Ejecutar el proyecto

```bash
# Levantar todos los servicios
docker-compose up --build

# O en modo detached
docker-compose up -d --build
```

### Acceso a los servicios

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080/api
- **Keycloak Admin**: http://localhost:8180 (admin/admin)

## 🔐 Autenticación

El sistema usa **Keycloak** para autenticación OAuth2/OIDC.

### Usuarios preconfigrados:

| Usuario | Contraseña | Rol |
|---------|-----------|-----|
| detective | password | user |
| admin | admin | admin, user |

### Flujo de autenticación:

1. El usuario accede al frontend (puerto 3000)
2. Se redirige automáticamente a Keycloak para login
3. Keycloak valida credenciales y genera un JWT token
4. El token se envía en el header `Authorization: Bearer <token>` a cada petición del backend
5. El backend valida el token con Keycloak y autoriza la petición

### Configuración del Realm:

El archivo `keycloak-init/investigation-realm.json` contiene:
- Realm: `investigation`
- Client: `investigation-frontend` (público, para SPA)
- Usuarios de prueba con contraseñas
- Roles y permisos

## 📡 API Endpoints

Todos los endpoints requieren autenticación (Bearer token):

### Cases
- `GET /api/cases` - Listar casos
- `POST /api/cases` - Crear caso
- `GET /api/cases/{id}` - Obtener caso
- `PUT /api/cases/{id}` - Actualizar caso
- `DELETE /api/cases/{id}` - Eliminar caso

### Detectives
- `GET /api/detectives`
- `POST /api/detectives`
- `GET /api/detectives/{id}`
- `PUT /api/detectives/{id}`
- `DELETE /api/detectives/{id}`

### Victims, Suspects, Evidence
- Similar estructura CRUD para `/api/victims`, `/api/suspects`, `/api/evidence`

## 🛠️ Desarrollo

### Detener servicios
```bash
docker-compose down
```

### Ver logs
```bash
docker-compose logs -f backend
docker-compose logs -f keycloak
```

### Reconstruir después de cambios
```bash
docker-compose down
docker-compose up --build
```

## 🔍 Troubleshooting

### Error 401 Unauthorized
- Verifica que Keycloak esté corriendo: http://localhost:8180
- Verifica que el realm `investigation` exista
- Verifica que el client `investigation-frontend` esté configurado
- Revisa los logs del backend: `docker-compose logs backend`

### Frontend no carga
- Verifica que el puerto 3000 esté disponible
- Verifica que el script de Keycloak se cargue: http://localhost:8180/js/keycloak.js

### Backend no conecta a PostgreSQL
- Verifica que PostgreSQL esté corriendo: `docker-compose ps`
- Verifica las variables de entorno en `compose.yml`

## 📝 Variables de entorno

El `compose.yml` define:

```yaml
Backend:
- SPRING_DATASOURCE_URL
- KEYCLOAK_ISSUER_URI

Keycloak:
- KEYCLOAK_ADMIN
- KEYCLOAK_ADMIN_PASSWORD
- KC_DB (postgres)
```

## 🎯 Características

- ✅ Autenticación OAuth2/OIDC con Keycloak
- ✅ JWT tokens con refresh automático
- ✅ CRUD completo para Cases, Detectives, Victims, Suspects, Evidence
- ✅ Relaciones entre entidades (JPA)
- ✅ CORS configurado
- ✅ Dockerizado completamente
- ✅ Base de datos PostgreSQL con init scripts

## 📚 Documentación adicional

- [Diagrama de clases](docs/ClassDiagram.md)
- [Diagrama ER](docs/ERD.md)
Tarea SpringBoot con docker 
