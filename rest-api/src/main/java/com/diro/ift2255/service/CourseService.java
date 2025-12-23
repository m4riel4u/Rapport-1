package com.diro.ift2255.service;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.Schedule;
import com.diro.ift2255.util.HttpClientApi;
import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.*;

public class CourseService {
    private final HttpClientApi clientApi;
    private static final String BASE_URL = "https://planifium-api.onrender.com/api/v1/courses";

    public CourseService(HttpClientApi clientApi) {
        this.clientApi = clientApi;
    }

    /** Fetch all courses */
    public List<Course> getAllCourses(Map<String, String> queryParams) {
        Map<String, String> params = (queryParams == null) ? Collections.emptyMap() : queryParams;

        URI uri = HttpClientApi.buildUri(BASE_URL, params);
        List<Course> courses = clientApi.get(uri, new TypeReference<List<Course>>() {});

        return courses;
    }

    /** Fetch a course by ID */
    public Optional<Course> getCourseById(String courseId) {
        return getCourseById(courseId, null);
    }

    /** Fetch a course by ID with optional query params */
    public Optional<Course> getCourseById(String courseId, Map<String, String> queryParams) {
        Map<String, String> params = (queryParams == null) ? Collections.emptyMap() : queryParams;
        URI uri = HttpClientApi.buildUri(BASE_URL + "/" + courseId, params);

        try {
            Course course = clientApi.get(uri, Course.class);
            return Optional.of(course);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
    /**Fetch a course by query */
    public List<Course> searchCourses(String query) {
        if (query == null || query.isEmpty()){
            return getAllCourses(null); //Retourne tous les cours si c'est vide
        }
        String lowerQuery = query.toLowerCase(); 

        List<Course> allCourses = getAllCourses(null);
        List<Course> filteredCourses = new ArrayList<>();
        for (Course cours : allCourses){
            if (cours.getId().toLowerCase().contains(lowerQuery) || cours.getName().toLowerCase().contains(lowerQuery)){
                filteredCourses.add(cours);
            }
        }
        return filteredCourses;
    }
    /**Fetch course details */
    public Optional<Course> getCompleteCourse(String CourseId){
        Map<String, String> params = Map.of("complete", "true", "include_schedule", "true");
        URI uri = HttpClientApi.buildUri("https://planifium-api.onrender.com/api/v1/courses/" + CourseId, params);

        try{
            Course course = clientApi.get(uri, Course.class);
            return Optional.of(course);

        }catch (RuntimeException e){
            return Optional.empty();
        }
        
    }
    //Méthode pour formater toutes les info données avec les horaires 
    public List<Map<String,Object>> rebuild(Course course) {
        List<Map<String,Object>> out = new ArrayList<>();
        // Organisation des données : schedules -> sections -> teachers, volets -> activities
        // Plusieurs schedules avec plusieurs sections internes avec plusieurs volets internes avec plusieurs activities
        for (Schedule sem : course.getSchedules()) {
            //Récupérer les infos des différentes schedules
            for (Schedule.Section sec : sem.sections) {
                //Faire une Linked Hash Map pour avoir toutes les infos donnée dans les sections
                Map<String,Object> h = new LinkedHashMap<>();
                h.put("section", sec.name);
                h.put("teachers", sec.teachers);
                h.put("semester", sem.semester);

                List<String> horaires = new ArrayList<>();
                String intra = null;
                String finale = null;
                //Récupérer infos dans les volets (cours théoriques, pratiques, intra et final)
                for (Schedule.Volet v : sec.volets) {
                    //Récupérer les dates/horaires des exams
                    if (v.name.equals("Intra")) {
                        intra = formatExam(v);
                    } else if (v.name.equals("Final")) {
                        finale = formatExam(v);
                    } else { // TH / TP
                        //Formatage des horaires
                        for (Schedule.Activity a : v.activities) {
                            horaires.add(
                                String.join(",", a.days)
                                + " " + a.start_time + "-" + a.end_time
                                + " (" + a.room + ")"
                            );
                        }
                    }
                }

                h.put("horaire", horaires);
                h.put("intra", intra);
                h.put("final", finale);

                out.add(h);
            }
        }
        return out;
    }
    //méthode privé pour formater les exams
    private String formatExam(Schedule.Volet volet) {
        if (volet.activities == null || volet.activities.isEmpty()) {
            return "Non spécifié";
        }

        Schedule.Activity act = volet.activities.get(0);

        return String.join(",", act.days)
                + " " + act.start_time + "-" + act.end_time
                + " (" + (act.room != null ? act.room : "??") + ")";
    }
    /**Fetch course selon programmes */
    public List<Course> getCourseByProgram(String program){
        Map<String, String> params = (null);
        URI uri = HttpClientApi.buildUri("https://planifium-api.onrender.com/api/v1/programs?programs_list=" + program, params);
        System.out.println(">>> appel API pour program = " + program);
        System.out.println(">>> URI = " + uri);
        try{
            Course[] courses = clientApi.get(uri, Course[].class);
            System.out.println(">>> réponse API OK : " + courses);
            return java.util.Arrays.asList(courses);

        }catch (RuntimeException e){
            System.out.println(">>> ERREUR API : " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
        
    }

}
