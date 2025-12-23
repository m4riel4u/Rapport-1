package com.diro.ift2255.util;

/**
 * La classe {@code HttpClientApiResponse} représente une réponse d'une requête HTTP effectuée via un client API.
 * Elle englobe le code de statut HTTP, le message de statut associé et le corps de la réponse.
 */
public class HttpClientApiResponse {

    /**
     * Le code de statut HTTP retourné par la requête (par exemple, 200 pour OK, 404 pour Not Found).
     */
    private final int statusCode;

    /**
     * Le message (état) de statut HTTP associé au code de statut.
     */
    private final String statusMessage;

    /**
     * Le body de la réponse HTTP.
     */
    private final String body;


    /**
     * Constructeur pour initialiser une réponse HTTP.
     * @param statusCode Le code de statut HTTP.
     * @param message Le message (état) de statut HTTP associé.
     * @param body Le body de la réponse HTTP.
     */
    public HttpClientApiResponse(int statusCode, String message, String body) {
        this.statusCode = statusCode;
        this.statusMessage = message;
        this.body = body;
    }

    /**
     * Récupère le code de statut HTTP de la réponse.
     * @return Le code de statut HTTP.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Récupère le message (état) de statut HTTP de la réponse.
     * @return Le message (état) de statut HTTP.
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Récupère le body de la réponse HTTP.
     * @return Le body de la réponse.
     */
    public String getBody() {
        return body;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la réponse HTTP.
     * Cette méthode fournit une vue d'ensemble du code de statut, du message et du body de la réponse.
     * @return Une chaîne représentant l'ensemble des informations de la réponse HTTP.
     */
    @Override
    public String toString() {
        return "Status Code: " + statusCode + ", Message: " + statusMessage + ", Body: " + body;
    }
}
