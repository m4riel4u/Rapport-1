package com.diro.ift2255.util;

import java.util.Map;

/**
 * La classe {@code HttpStatus} fournit une méthode utilitaire pour obtenir les statuts HTTP.
 * Elle contient les codes de statut HTTP courants et leurs états correspondantes.
 */
public class HttpStatus {
    private static final Map<Integer, String> REASONS = Map.ofEntries(
        Map.entry(200, "OK"),
        Map.entry(201, "Created"),
        Map.entry(400, "Bad Request"),
        Map.entry(401, "Unauthorized"),
        Map.entry(403, "Forbidden"),
        Map.entry(404, "Not Found"),
        Map.entry(500, "Internal Server Error")
    );

    /**
     * Retourne l'état associée à un code de statut HTTP.
     * Si le code n'est pas connu, la méthode renvoie "Unknown".
     * @param code Le code de statut HTTP pour lequel on souhaite obtenir l'état.
     * @return L'état associée au code de statut HTTP, ou "Unknown" si le code n'est pas connu.
     */
    public static String reasonPhrase(int code) {
        return REASONS.getOrDefault(code, "Unknown");
    }
}
