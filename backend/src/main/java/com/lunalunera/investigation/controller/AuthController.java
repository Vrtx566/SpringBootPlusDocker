package com.lunalunera.investigation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    @Value("${keycloak.url:http://keycloak:8080}")
    private String keycloakUrl;

    @Value("${keycloak.realm:investigation}")
    private String realm;

    @Value("${keycloak.client-id:investigation-frontend}")
    private String clientId;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // URL del token endpoint de Keycloak
            String tokenUrl = keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token";

            // Preparar el body para Keycloak
            String body = "grant_type=password" +
                    "&client_id=" + clientId +
                    "&username=" + request.getUsername() +
                    "&password=" + request.getPassword();

            // Hacer la petición a Keycloak
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                TokenResponse tokenResponse = new TokenResponse(
                        jsonNode.get("access_token").asText(),
                        jsonNode.get("token_type").asText(),
                        jsonNode.get("expires_in").asInt(),
                        jsonNode.get("refresh_token").asText()
                );
                return ResponseEntity.ok(tokenResponse);
            } else {
                return ResponseEntity.status(response.getStatusCode())
                        .body(new ErrorResponse("Authentication failed"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ErrorResponse("Error during authentication: " + e.getMessage()));
        }
    }

    // DTOs
    public static class LoginRequest {
        public String username;
        public String password;

        public LoginRequest() {}

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class TokenResponse {
        public String access_token;
        public String token_type;
        public int expires_in;
        public String refresh_token;

        public TokenResponse(String access_token, String token_type, int expires_in, String refresh_token) {
            this.access_token = access_token;
            this.token_type = token_type;
            this.expires_in = expires_in;
            this.refresh_token = refresh_token;
        }
    }

    public static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}

