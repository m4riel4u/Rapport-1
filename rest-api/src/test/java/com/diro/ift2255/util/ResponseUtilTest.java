package com.diro.ift2255.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ResponseUtilTest {

    @Test
    void testFormatError() {
        Map<String, String> result = ResponseUtil.formatError("Message d'erreur");
        assertTrue(result.containsKey("error"), "La map retournée devrait contenir la clé 'error'.");
        assertEquals("Message d'erreur", result.get("error"), "La valeur associée à la clé 'error' devrait être 'Message d'erreur'.");
    }
}