package com.diro.ift2255.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.EligibilityResult;
import com.diro.ift2255.model.User;
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
     * Test : vérifier un test d'erreur avec l'API qui retourne une liste vide.
     */
    @Test
    void testGetAllCourses_apiRetourneListeVide() {
        // ARRANGE
        when(clientApi.get(any(URI.class), any(TypeReference.class))).thenReturn(Collections.emptyList());

        // ACT
        var result = courseService.getAllCourses(null);

        // ASSERT
        assertEquals(0, result.size(),
            "La liste des cours devrait être vide, puisque l'API retourne une liste vide.");
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
     * Test : vérifier que searchCourses filtre correctement selon l'ID ou un nom.
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

    /**
     * Test : vérifier que searchCourses fonctionne avec une requête null (devrait retourner tous les cours).
     */
    @Test
    void testSearchCourses_nullQuery() {
        // ARRANGE
        when(clientApi.get(any(URI.class), any(TypeReference.class))).thenReturn(fauxCours);

        // ACT
        var result = courseService.searchCourses(null);

        // ASSERT
        assertEquals(2, result.size(), "Une requête null devrait retourner tous les cours.");
    }

    /**
     * Test : vérifier que la recherche est insensible à la casse.
     */
    @Test
    void testSearchCourses_upperCase() {
        // ARRANGE
        when(clientApi.get(any(URI.class), any(TypeReference.class))).thenReturn(fauxCours);

        // ACT : recherche en majuscules pour "LOGICIEL"
        var resultUpper = courseService.searchCourses("LOGICIEL");

        // ASSERT
        assertEquals(1, resultUpper.size(), "La recherche devrait être insensible à la casse.");
        assertEquals("IFT2255", resultUpper.get(0).getId());
    }

    /**
     * Test : vérifier que la recherche est insensible à la casse.
     */
     @Test
    void testSearchCourses_lowerCase() {
        // ARRANGE
        when(clientApi.get(any(URI.class), any(TypeReference.class))).thenReturn(fauxCours);

        // ACT : recherche en minuscules pour "design"
        var resultLower = courseService.searchCourses("design");

        // ASSERT
        assertEquals(1, resultLower.size(), "La recherche devrait être insensible à la casse.");
        assertEquals("IFT1005", resultLower.get(0).getId());
    }

    /**
     * Test : vérifier qu'une recherche sans correspondance retourne une liste vide.
     */
    @Test
    void testSearchCourses_noMatches() {
        // ARRANGE
        when(clientApi.get(any(URI.class), any(TypeReference.class))).thenReturn(fauxCours);

        // ACT : recherche pour un terme qui n'existe pas
        var result = courseService.searchCourses("informatique");

        // ASSERT
        assertTrue(result.isEmpty(), "Une recherche sans correspondance devrait retourner une liste vide.");
    }

    /**
     * Test : vérifier la recherche avec plusieurs correspondances.
     */
    @Test
    void testSearchCourses_multipleMatches() {
        // ARRANGE
        when(clientApi.get(any(URI.class), any(TypeReference.class))).thenReturn(fauxCours);

        // ACT : recherche pour "IFT" qui devrait correspondre aux deux cours
        var result = courseService.searchCourses("IFT");

        // ASSERT
        assertEquals(2, result.size(), "La recherche devrait retourner plusieurs correspondances.");
        // Vérifier que les deux cours sont présents
        boolean hasIFT2255 = result.stream().anyMatch(c -> c.getId().equals("IFT2255"));
        boolean hasIFT1005 = result.stream().anyMatch(c -> c.getId().equals("IFT1005"));
        assertTrue(hasIFT2255, "IFT2255 devrait être dans les résultats.");
        assertTrue(hasIFT1005, "IFT1005 devrait être dans les résultats.");
    }

    /**
     * Test : vérifier que checkEligibility retourne ineligible quand le cours n'existe pas.
     */
    @Test
    void testCheckEligibility_courseNotFound() {
        // ARRANGE
        when(clientApi.get(any(URI.class), eq(Course.class))).thenThrow(new RuntimeException("Course not found"));

        // ACT
        EligibilityResult result = courseService.checkEligibility(List.of("IFT1005"), "INVALID123");

        // ASSERT
        assertTrue(!result.eligible, "Le résultat devrait être ineligible.");
        assertEquals(List.of("Cours introuvable"), result.missing, "Le message d'erreur devrait être correct.");
    }

    /**
     * Test : vérifier que checkEligibility retourne eligible quand il n'y a pas de prérequis.
     */
    @Test
    void testCheckEligibility_noPrerequisites() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(null); // No prerequisites

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibility(List.of(), "IFT2255");

        // ASSERT
        assertTrue(result.eligible, "Le résultat devrait être eligible.");
        assertTrue(result.missing.isEmpty(), "La liste des cours manquants devrait être vide.");
    }

    /**
     * Test : vérifier que checkEligibility retourne eligible quand tous les prérequis sont complétés.
     */
    @Test
    void testCheckEligibility_allPrerequisitesCompleted() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(List.of("IFT1005", "IFT1010"));

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibility(List.of("IFT1005", "IFT1010", "IFT1020"), "IFT2255");

        // ASSERT
        assertTrue(result.eligible, "Le résultat devrait être eligible.");
        assertTrue(result.missing.isEmpty(), "La liste des cours manquants devrait être vide.");
    }

    /**
     * Test : vérifier que checkEligibility retourne ineligible avec les prérequis manquants.
     */
    @Test
    void testCheckEligibility_missingPrerequisites() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(List.of("IFT1005", "IFT1010", "IFT1020"));

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibility(List.of("IFT1005"), "IFT2255");

        // ASSERT
        assertTrue(!result.eligible, "Le résultat devrait être ineligible.");
        assertEquals(List.of("IFT1010", "IFT1020"), result.missing, "Les prérequis manquants devraient être listés.");
    }

    /**
     * Test : vérifier que checkEligibility retourne ineligible quand aucun prérequis n'est complété.
     */
    @Test
    void testCheckEligibility_noPrerequisitesCompleted() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(List.of("IFT1005", "IFT1010"));

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibility(List.of(), "IFT2255");

        // ASSERT
        assertTrue(!result.eligible, "Le résultat devrait être ineligible.");
        assertEquals(List.of("IFT1005", "IFT1010"), result.missing, "Tous les prérequis devraient être manquants.");
    }

    /**
     * Test : vérifier que checkEligibilityForUser retourne eligible quand l'utilisateur a tous les prérequis.
     */
    @Test
    void testCheckEligibilityForUser_eligible() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(List.of("IFT1005", "IFT1010"));

        User user = new User(1, "Alice", "alice@example.com", "bachelor", List.of("IFT1005", "IFT1010", "IFT1020"));

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibilityForUser(user, "IFT2255");

        // ASSERT
        assertTrue(result.eligible, "L'utilisateur devrait être eligible car il a tous les prérequis.");
        assertTrue(result.missing.isEmpty(), "Il ne devrait pas y avoir de prérequis manquants.");
    }

    /**
     * Test : vérifier que checkEligibilityForUser retourne ineligible quand l'utilisateur manque des prérequis.
     */
    @Test
    void testCheckEligibilityForUser_ineligible() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(List.of("IFT1005", "IFT1010"));

        User user = new User(1, "Bob", "bob@example.com", "bachelor", List.of("IFT1005"));

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibilityForUser(user, "IFT2255");

        // ASSERT
        assertTrue(!result.eligible, "L'utilisateur devrait être ineligible car il manque un prérequis.");
        assertEquals(List.of("IFT1010"), result.missing, "IFT1010 devrait être manquant.");
    }

    /**
     * Test : vérifier que checkEligibilityForUser retourne eligible quand le cours n'a pas de prérequis.
     */
    @Test
    void testCheckEligibilityForUser_noPrerequisites() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(List.of());

        User user = new User(1, "Charlie", "charlie@example.com", "bachelor", List.of());

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibilityForUser(user, "IFT2255");

        // ASSERT
        assertTrue(result.eligible, "L'utilisateur devrait être eligible car il n'y a pas de prérequis.");
        assertTrue(result.missing.isEmpty(), "Il ne devrait pas y avoir de prérequis manquants.");
    }

    /**
     * Test : vérifier que checkEligibilityForUser retourne ineligible quand le cours n'existe pas.
     */
    @Test
    void testCheckEligibilityForUser_courseNotFound() {
        // ARRANGE
        User user = new User(1, "Alice", "alice@example.com", "bachelor", List.of("IFT1005"));

        when(clientApi.get(any(URI.class), eq(Course.class))).thenThrow(new RuntimeException("Course not found"));

        // ACT
        EligibilityResult result = courseService.checkEligibilityForUser(user, "INVALID123");

        // ASSERT
        assertTrue(!result.eligible, "Le résultat devrait être ineligible quand le cours n'existe pas.");
        assertEquals(List.of("Cours introuvable"), result.missing, "Il devrait y avoir un message d'erreur pour cours introuvable.");
    }

    /**
     * Test : vérifier que checkEligibilityForUser retourne ineligible quand l'utilisateur n'a aucun cours complété.
     */
    @Test
    void testCheckEligibilityForUser_noCompletedCourses() {
        // ARRANGE
        Course course = new Course();
        course.setId("IFT2255");
        course.setPrerequisite_courses(List.of("IFT1005", "IFT1010"));

        User user = new User(1, "Dave", "dave@example.com", "bachelor", List.of());

        when(clientApi.get(any(URI.class), eq(Course.class))).thenReturn(course);

        // ACT
        EligibilityResult result = courseService.checkEligibilityForUser(user, "IFT2255");

        // ASSERT
        assertTrue(!result.eligible, "L'utilisateur devrait être ineligible car il n'a aucun cours complété.");
        assertEquals(List.of("IFT1005", "IFT1010"), result.missing, "Tous les prérequis devraient être manquants.");
    }
}


