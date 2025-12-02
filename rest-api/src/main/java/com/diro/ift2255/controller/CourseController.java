package com.diro.ift2255.controller;

import io.javalin.http.Context;
import com.diro.ift2255.model.Course;
import com.diro.ift2255.service.CourseService;
import com.diro.ift2255.util.ResponseUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class CourseController {
    // Service qui contient la logique métier pour la manipulation des cours et la communication avec les services externes
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    /**
     * Récupère la liste de tous les cours.
     * @param ctx Contexte Javalin représentant la requête et la réponse HTTP
     */
    public void getAllCourses(Context ctx) {
        Map<String, String> queryParams = extractQueryParams(ctx);

        List<Course> courses = service.getAllCourses(queryParams);
        ctx.json(courses);
    }

    /**
     * Récupère un cours spécifique par son ID.
     * @param ctx Contexte Javalin représentant la requête et la réponse HTTP
     */
    public void getCourseById(Context ctx) {
        String id = ctx.pathParam("id");

        if (!validateCourseId(id)) {
            ctx.status(400).json(ResponseUtil.formatError("Le paramètre id n'est pas valide."));
            return;
        }

        Optional<Course> course = service.getCourseById(id);
        if (course.isPresent()) {
            ctx.json(course.get());
        } else {
            ctx.status(404).json(ResponseUtil.formatError("Aucun cours ne correspond à l'ID: " + id));
        }
    }

    /**
     * Vérifie que l'ID du cours est bien formé
     * @param courseId L'ID du cours à valider
     * @return Valeur booléeene indiquant si l'ID est valide
     */
    private boolean validateCourseId(String courseId) {
        return courseId != null && courseId.trim().length() >= 6;
    }

    /**
     * Récupère tous les paramètres de requête depuis l'URL et les met dans une Map
     * @param ctx Contexte Javalin représentant la requête HTTP
     * @return Map contenant les paramètres de requête et leurs valeurs
     */
    private Map<String, String> extractQueryParams(Context ctx) {
        Map<String, String> queryParams = new HashMap<>();

        ctx.queryParamMap().forEach((key, values) -> {
            if (!values.isEmpty()) {
                queryParams.put(key, values.get(0));
            }
        });

        return queryParams;
    }
    public void getCoursesByQuery (Context ctx){
        String query = ctx.queryParam("query");
        List<Course> results = service.searchCourses(query);
        ctx.json(results);

    }
    public void getCompleteCourse(Context ctx) {
    String id = ctx.pathParam("id").trim().toUpperCase();

    service.getCompleteCourse(id).ifPresentOrElse(
        course -> {

            List<Map<String, Object>> rebuiltSchedules = service.rebuild(course);

            // Créer un Map combiné pour le front-end
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("id", course.getId());
            response.put("name", course.getName());
            response.put("description", course.getDescription());
            response.put("credits", course.getCredits());
            response.put("schedules", rebuiltSchedules);
            response.put("prerequisite_courses", course.getPrerequisite_courses());
            response.put("equivalent_courses", course.getEquivalent_courses());
            response.put("concomitant_courses", course.getConcomitant_courses());
            Map<String, Boolean> termsFR = new LinkedHashMap<>();
            course.getAvailable_terms().forEach((key, value) -> {
                String keyFR;
                switch (key.toLowerCase()) {
                    case "autumn": keyFR = "Automne"; break;
                    case "winter": keyFR = "Hiver"; break;
                    case "summer": keyFR = "Ete"; break;
                    default: keyFR = key;
                }
                termsFR.put(keyFR, value);
            });
            response.put("available_terms", termsFR);
            Map<String, Boolean> periodsFR = new LinkedHashMap<>();
            course.getAvailable_periods().forEach((key, value) -> {
                String keyFR;
                switch (key.toLowerCase()) {
                    case "daytime": keyFR = "Jour"; break;
                    case "evening": keyFR = "Soir"; break;
                    default: keyFR = key;
                }
                periodsFR.put(keyFR, value);
            });
            response.put("available_periods", periodsFR);

            
            ctx.json(response);
        },
        () -> {

            ctx.status(404).json(Map.of("error", "Cours introuvable"));
        }
    );
}

}
