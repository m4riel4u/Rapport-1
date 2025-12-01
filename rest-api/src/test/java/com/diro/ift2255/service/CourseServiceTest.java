package com.diro.ift2255.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.util.HttpClientApi;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests unitaires pour CourseService en utilisant Mockito pour mocker HttpClientApi.
 * On test le comportement de CourseService sans appeler API Planifium.
 */
@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    private CourseService courseService;

    @Mock
    private HttpClientApi clientApi;

    private Course c1;
    private Course c2;
    private List<Course> fauxCours;

    @BeforeAll
    // Exécuté une seule fois avant tous les tests (non utilisé ici)
    static void initAll() {
    }

    @BeforeEach
    void setup() {
        // Création de quelques cours fictifs simulant des résultats d'API
        fauxCours = new ArrayList<>();

        c1 = new Course();
        c1.setId("IFT2255");
        c1.setName("Génie logiciel");
        fauxCours.add(c1);

        c2 = new Course();
        c2.setId("IFT1005");
        c2.setName("Design et développement Web");
        fauxCours.add(c2);

        // Injection du mock dans le service
        courseService = new CourseService(clientApi);
    }

    /**
     * Test : vérifier que getAllCourses retourne la liste complète des cours.
     */
    @Test
    void testGetAllCourses() {
        // ARRANGE : on simule la réponse de clientApi.get... pour getAllCourses
        when(clientApi.get(
                any(URI.class),
                any(TypeReference.class)
        )).thenReturn(fauxCours);

        // ACT
        var courses = courseService.getAllCourses(null);

        // ASSERT
        assertEquals(2, courses.size(), "Il devrait y avoir 2 cours initialement.");
    }

    /**
     * Test : récupérer un cours par ID via getCourseById.
     */
    @Test
    void testGetCourseById() {
        // ARRANGE
        String courseId = "IFT2255";

        // on simule l'appel clientApi.get(uri, Course.class) utilisé par getCourseById(...)
        when(clientApi.get(
                any(URI.class),
                eq(Course.class)
        )).thenReturn(c1);

        // ACT
        Optional<Course> courseOpt = courseService.getCourseById(courseId);

        // ASSERT
        assertTrue(courseOpt.isPresent(), "Le cours avec l'ID IFT2255 devrait exister.");
        assertEquals("IFT2255", courseOpt.get().getId(), "L'ID du cours retourné devrait être IFT2255.");
        assertEquals("Génie logiciel", courseOpt.get().getName(),
                "Le nom du cours IFT2255 devrait être 'Génie logiciel'.");
    }

    /**
     * Test : vérifier que searchCourses filtre correctement selon l'ID ou un nom (partiel ou complet).
     */
    @Test
    void testSearchCourses_filtreParIdOuNom() {
        // ARRANGE : searchCourses() appelle getAllCourses(null),
        when(clientApi.get(
                any(URI.class),
                any(TypeReference.class)
        )).thenReturn(fauxCours);

        // ACT : recherche par ID
        var resultId = courseService.searchCourses("IFT2255");

        // ASSERT
        assertEquals(1, resultId.size(), "La recherche par ID IFT2255 devrait retourner 1 cours.");
        assertEquals("IFT2255", resultId.get(0).getId());

        // ACT : recherche par nom (peut être partiel et accepte majuscule ou minuscule)
        var resultNom = courseService.searchCourses("design");

        // ASSERT
        assertEquals(1, resultNom.size(), "La recherche par 'design' devrait retourner 1 cours.");
        assertEquals("IFT1005", resultNom.get(0).getId());
    }

    /**
     * Test : si le champ de requête est vide, searchCourses doit retourner tous les cours.
     */
    @Test
    void testSearchCourses_champVide() {
        // ARRANGE
        when(clientApi.get(
                any(URI.class),
                any(TypeReference.class)
        )).thenReturn(fauxCours);

        // ACT
        var result = courseService.searchCourses("");

        // ASSERT
        assertEquals(2, result.size(), "Une requête vide devrait retourner tous les cours.");
    }
}

