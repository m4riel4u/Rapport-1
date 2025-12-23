package com.diro.ift2255.util;

import java.util.Map;

/**
 * La classe {@code ResponseUtil} fournit des méthodes utilitaires pour formater les réponses d'erreur dans une application.
 * Elle permet de retourner un format standardisé pour les messages d'erreur.
 */
public class ResponseUtil {

    /**
     * Formate le message d'erreur.
     * @param errorMessage Le message d'erreur à formater.
     * @return Le message d'erreur associé.
     */
    public static Map<String, String> formatError(String errorMessage) {
        return Map.of("error", errorMessage);
    }
}
