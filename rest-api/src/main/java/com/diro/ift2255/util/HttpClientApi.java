package com.diro.ift2255.util;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * La classe {@code HttpClientApi} fournit une API pour effectuer des requêtes HTTP
 * et gérer les réponses avec des fonctionnalités telles que la gestion des erreurs, le traitement du body des réponses en JSON,
 * ainsi que la construction dynamique des URI avec des paramètres de requête.
 */
public class HttpClientApi {

    /**
     * Le client HTTP utilisé pour envoyer les requêtes.
     */
    private final HttpClient client;

    /**
     * L'ObjectMapper utilisé pour convertir les données JSON en objets Java.
     */
    private ObjectMapper mapper;

    /**
     * Constructeur par défaut qui initialise le client HTTP avec un délai de connexion de 10 secondes
     * et crée un ObjectMapper pour le traitement du JSON.
     */
    public HttpClientApi() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.mapper = new ObjectMapper();
    }

    /**
     * Effectue une requête GET et retourne une réponse encapsulée dans un objet {@link HttpClientApiResponse}.
     * @param uri L'URI de la requête GET.
     * @return Un objet {@link HttpClientApiResponse} contenant le code de statut, le message et le body de la réponse.
     */
    public HttpClientApiResponse get(URI uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new HttpClientApiResponse(
                    response.statusCode(),
                    HttpStatus.reasonPhrase(response.statusCode()),
                    response.body());

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt(); // best practice if interrupted
            return new HttpClientApiResponse(500, "Internal Server Error", e.getMessage());
        }
    }

    /** GET and map JSON body to a given class */
    /**
     * Effectue une requête GET et mappe le body JSON de la réponse vers une classe Java donnée.
     * @param <T> Le type de la classe cible.
     * @param uri L'URI de la requête GET.
     * @param clazz La classe cible vers laquelle le corps JSON doit être mappé.
     * @return Un objet de type {@code T} qui représente les données de la réponse.
     * @throws RuntimeException Si la requête échoue ou si la désérialisation JSON échoue.
     */
    public <T> T get(URI uri, Class<T> clazz) {
        HttpClientApiResponse raw = get(uri);
        if (raw.getStatusCode() >= 200 && raw.getStatusCode() < 300) {
            try {
                return mapper.readValue(raw.getBody(), clazz);
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse JSON: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Request failed: " + raw.getStatusCode() + " - " + raw.getStatusMessage());
        }
    }

    /** GET and map JSON body to collection or complex type */
    /**
     * Effectue une requête GET et mappe le corps JSON de la réponse vers un type ou une collection complexe.
     * @param <T> Le type de la collection ou du type complexe.
     * @param uri L'URI de la requête GET.
     * @param typeRef La référence de type pour la désérialisation (ex: {@link TypeReference}).
     * @return Un objet de type {@code T} représentant les données de la réponse.
     * @throws RuntimeException Si la requête échoue ou si la désérialisation JSON échoue.
     */
    public <T> T get(URI uri, TypeReference<T> typeRef) {
        HttpClientApiResponse raw = get(uri);
        if (raw.getStatusCode() >= 200 && raw.getStatusCode() < 300) {
            try {
                return mapper.readValue(raw.getBody(), typeRef);
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse JSON: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Request failed: " + raw.getStatusCode() + " - " + raw.getStatusMessage());
        }
    }

    /** Perform a POST request with JSON body */
    /**
     * Effectue une requête POST avec un body JSON.
     * @param uri L'URI de la requête POST.
     * @param jsonBody Le corps de la requête au format JSON.
     * @return Un objet {@link HttpClientApiResponse} contenant le code de statut, le message et le corps de la réponse.
     */
    public HttpClientApiResponse post(URI uri, String jsonBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return new HttpClientApiResponse(
                    response.statusCode(),
                    HttpStatus.reasonPhrase(response.statusCode()),
                    response.body());

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return new HttpClientApiResponse(500, "Internal Server Error", e.getMessage());
        }
    }

    /** Helper to build URIs with query parameters */
    /**
     * Méthode pour construire une URI avec des paramètres de requête.
     * @param baseUrl L'URL de base de l'API.
     * @param params Les paramètres de la requête.
     * @return L'URI complète avec les paramètres de requête encodés.
     */
    public static URI buildUri(String baseUrl, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(baseUrl);
        if (params != null && !params.isEmpty()) {
            sb.append("?");
            params.forEach((key, value) -> {
                sb.append(URLEncoder.encode(key, StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                        .append("&");
            });
            sb.deleteCharAt(sb.length() - 1); // remove trailing &
        }
        return URI.create(sb.toString());
    }
}
