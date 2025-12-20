package com.diro.ift2255.controller;

import io.javalin.http.Context;
import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.EligibilityResult;
import com.diro.ift2255.service.CourseService;
import com.diro.ift2255.util.ResponseUtil;

import java.util.ArrayList;
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
            response.put("class_average", course.getClass_average());
            response.put("difficulty_score", course.getDifficulty_score());
            response.put("class_difficulty", course.getClass_difficulty());
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
    public void getCoursesByProgram (Context ctx){
        String program = ctx.pathParam("program");
        List<Course> results = service.getCourseByProgram(program);
        ctx.json(results);

    }
    public void getCoursesByProgramWithSchedule(Context ctx) {
    String program = ctx.pathParam("program");
    System.out.println(">>> getCoursesByProgramWithSchedule called, program=" + program);

    if (program == null || program.isEmpty()) {
        ctx.status(400).json(Map.of("error", "Le code du programme est requis"));
        return;
    }

    String includeScheduleParam = ctx.queryParam("includeSchedule");
    boolean includeSchedule = includeScheduleParam != null && includeScheduleParam.equalsIgnoreCase("true");
    String semester = ctx.queryParam("semester"); 
    System.out.println(">>> includeSchedule=" + includeSchedule + ", semester=" + semester);

    List<Course> courses = service.getCoursesByProgramWithSchedule(program, includeSchedule, semester);
    System.out.println(">>> courses.size()=" + courses.size());

    if (courses.isEmpty()) {
        ctx.status(404).json(Map.of("error", "Aucun cours trouvé pour ce programme et trimestre."));
        return;
    }

    if (includeSchedule) {
        List<Map<String, Object>> response = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> courseMap = new LinkedHashMap<>();
            courseMap.put("id", course.getId());
            courseMap.put("name", course.getName());
            courseMap.put("description", course.getDescription());
            courseMap.put("credits", course.getCredits());
            courseMap.put("schedules", service.rebuild(course));
            response.add(courseMap);
        }
        ctx.json(response);
    } else {
        ctx.json(courses);
    }
}
    public void checkEligibility(Context ctx) {
        System.out.println(">>> checkEligibility CALLED");

        Map<String, Object> body = ctx.bodyAsClass(Map.class);

        List<String> completedCourses = (List<String>) body.get("completedCourses");
        String targetCourse = (String) body.get("targetCourse");

        System.out.println("Cours complétés : " + completedCourses);
        System.out.println("Cours cible : " + targetCourse);

        EligibilityResult result = service.checkEligibility(completedCourses, targetCourse);

        ctx.json(result);
    }




}
