# Guía de Solución de Errores 401 Unauthorized

## ❌ Error: GET http://localhost:3000/api/... 401 (Unauthorized)

Este error ocurre cuando el backend rechaza las peticiones por falta de autenticación válida.

## ✅ Solución Implementada

### 1. **Configuración de Keycloak** ✔️

El archivo `keycloak-init/investigation-realm.json` configura automáticamente:
- Realm: `investigation`
- Client: `investigation-frontend`
- Usuarios: `detective/password` y `admin/admin`

### 2. **Frontend con Keycloak JS** ✔️

El `index.html` ahora:
- Carga la librería de Keycloak desde `http://localhost:8180/js/keycloak.js`
- Se inicializa con `onLoad: 'login-required'`
- Envía el token JWT en cada petición: `Authorization: Bearer <token>`
- Refresca el token automáticamente cada minuto

### 3. **Backend como Resource Server** ✔️

El `SecurityConfig.java`:
- Valida tokens JWT con `.oauth2ResourceServer()`
- Usa el `issuer-uri` de Keycloak para verificar tokens
- Permite CORS desde el frontend

### 4. **Variables de entorno** ✔️

El `application.yml` y `compose.yml` configuran:
```yaml
KEYCLOAK_ISSUER_URI: http://keycloak:8080/realms/investigation
```

## 🚀 Pasos para ejecutar

1. **Iniciar servicios**:
   ```powershell
   .\start.ps1
   ```
   O manualmente:
   ```bash
   docker-compose up --build
   ```

2. **Esperar que Keycloak esté listo** (~30 segundos):
   ```bash
   docker-compose logs -f keycloak
   ```
   Busca: "Keycloak ... started"

3. **Acceder al frontend**:
   - Abre http://localhost:3000
   - Serás redirigido a Keycloak automáticamente
   - Login con: `detective` / `password`

4. **Verificar autenticación**:
   - En la consola del navegador (F12), deberías ver:
     ```
     Authenticated as: detective
     ```
   - Las peticiones API incluirán el header `Authorization: Bearer eyJhbG...`

## 🔍 Verificación Manual

### Verificar que Keycloak funciona:
```bash
# Acceder a Keycloak Admin
http://localhost:8180
# Login: admin / admin
# Verificar que exista el realm "investigation"
```

### Verificar el token JWT:
1. Abre la consola del navegador (F12)
2. Ejecuta:
   ```javascript
   console.log(keycloak.token)
   ```
3. Copia el token y pégalo en https://jwt.io para ver su contenido

### Probar el backend directamente:
```powershell
# Primero, obtener un token de Keycloak
$body = @{
    grant_type = "password"
    client_id = "investigation-frontend"
    username = "detective"
    password = "password"
} | ConvertTo-Json

$response = Invoke-RestMethod -Uri "http://localhost:8180/realms/investigation/protocol/openid-connect/token" -Method POST -Body $body -ContentType "application/json"

$token = $response.access_token

# Ahora hacer una petición al backend con el token
$headers = @{
    "Authorization" = "Bearer $token"
}

Invoke-RestMethod -Uri "http://localhost:8080/api/cases" -Headers $headers
```

## 📋 Checklist de Troubleshooting

- [ ] Keycloak está corriendo: `docker-compose ps`
- [ ] El realm `investigation` existe en Keycloak Admin
- [ ] El client `investigation-frontend` está configurado con:
  - Public Client: ✅
  - Valid Redirect URIs: `http://localhost:3000/*`
  - Web Origins: `http://localhost:3000`
- [ ] El backend está corriendo: `curl http://localhost:8080/actuator/health`
- [ ] El frontend carga el script de Keycloak sin errores (F12)
- [ ] El token se está enviando en las peticiones (Network tab en F12)

## 🐛 Errores Comunes

### "Keycloak is not defined"
- El script de Keycloak no se cargó
- Verifica que http://localhost:8180/js/keycloak.js esté accesible
- Espera a que Keycloak termine de iniciar

### "Invalid token"
- El `issuer-uri` del backend no coincide con el realm de Keycloak
- Verifica `application.yml`: `issuer-uri: http://keycloak:8080/realms/investigation`
- Desde el navegador se accede como `localhost:8180`, pero el backend usa `keycloak:8080` (nombre del servicio)

### CORS errors
- Verifica que `SecurityConfig.java` incluya `http://localhost:3000` en `allowedOrigins`

## 📞 Soporte

Si sigues teniendo problemas:
1. Reinicia todos los servicios: `docker-compose down && docker-compose up --build`
2. Revisa los logs: `docker-compose logs backend keycloak`
3. Limpia volúmenes: `docker-compose down -v` (⚠️ borrará datos)

