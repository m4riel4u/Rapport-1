package com.diro.ift2255.model;

import java.util.List;

/**
 * La classe {@code EligibilityResult} représente le résultat de la vérification d'éligibilité
 * d'un utilisateur pour un cours donné, en indiquant si l'utilisateur est éligible ou non
 * ainsi que la liste des prérequis manquants si l'utilisateur n'est pas éligible.
 * 
 * Cette classe est utilisée pour englober les informations d'éligibilité d'un utilisateur
 * en fonction des cours qu'il a complétés.
 */
public class EligibilityResult {

    /**
     * Indique si l'utilisateur est éligible ou non pour le cours cible.
     * Si la valeur est {@code TRUE}, cela signifie que l'utilisateur est éligible.
     * Si la valeur est {@code FALSE}, l'utilisateur n'est pas éligible et des prérequis sont manquants.
     */
    public boolean eligible;

    /**
     * La liste des cours prérequis manquants pour que l'utilisateur soit éligible.
     * Cette liste est vide si l'utilisateur est éligible.
     */
    public List<String> missing;

    /**
     * Constructeur de la classe {@code EligibilityResult}.
     * 
     * @param eligible Un booléen indiquant si l'utilisateur est éligible ou non pour le cours.
     * @param missing Une liste des prérequis manquants si l'utilisateur n'est pas éligible.
     *                La liste est vide si l'utilisateur est éligible.
     */
    public EligibilityResult(boolean eligible, List<String> missing) {
        this.eligible = eligible;
        this.missing = missing;
    }
}