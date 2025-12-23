package com.diro.ift2255.util;

import java.util.regex.Pattern;

/**
 * La classe {@code ValidationUtil} contient des méthodes utilitaires pour valider des chaînes de caractères selon des critères spécifiques. 
 * Elle fournit des méthodes pour valider des courriels et vérifier qu'une chaîne n'est pas vide.
 */
public class ValidationUtil {

    /**
     * Le pattern utilisé pour valider les adresses courriels.
     * Il correspond à un format de courriel standard.
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");

    /**
     * Vérifie si la chaîne de caractères donnée est une adresse courriel valide.
     * Une adresse courriel est considérée comme valide si elle correspond au format standard.
     * @param email La chaîne de caractères à valider.
     * @return {@code TRUE} si le courriel est valide, sinon {@code FALSE}.
     */
    public static boolean isEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Vérifie si la chaîne de caractères donnée n'est pas vide.
     * Une chaîne est considérée comme non vide si elle n'est pas {@code null} 
     * et si elle contient au moins un caractère différent de l'espace blanc.
     * @param str La chaîne de caractères à vérifier.
     * @return {@code TRUE} si la chaîne n'est pas vide, sinon {@code FALSE}.
     */
    public static boolean isNotEmpty(String str) {
    return str != null && !str.isEmpty();
    }
}
