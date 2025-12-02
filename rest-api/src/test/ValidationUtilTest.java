package com.diro.ift2255.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ValidationUtilTest {

    @Test
    void testIsNotEmpty() {
        assertTrue(ValidationUtil.isNotEmpty("Hello"), "La chaîne 'Hello' n'est pas vide");
        assertFalse(ValidationUtil.isNotEmpty(""), "Une chaîne vide est détectée");
        assertFalse(ValidationUtil.isNotEmpty(null), "Valeur null est détectée");
    }

    @Test
    void testIsValidEmail() {
        assertTrue(ValidationUtil.isValidEmail("abc@mail.com"), "Email standard valide");
        assertFalse(ValidationUtil.isValidEmail("abc"), "Email sans @ non valide");
        assertFalse(ValidationUtil.isValidEmail(""), "Chaîne vide non valide");
        assertFalse(ValidationUtil.isValidEmail(null), "Valeur null non valide");
    }

    @Test
    testIsValidEmailEdgeCases() {
        assertTrue(ValidationUtil.isValidEmail("abc@mail.domain.com"), "Sous-domaine valide");
        assertFalse(ValidationUtil.isValidEmail("abc @mail.com"), "Email avec espace non valide");
        assertFalse(ValidationUtil.isValidEmail("abc@@mail.com"), "Email avec double @ non valide");
    }
}