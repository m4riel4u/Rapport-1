package com.diro.ift2255.service;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.util.HttpClientApi;
import com.fasterxml.jackson.core.type.TypeReference;
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
        Map<String, String> params = Map.of("complete", "true");
        URI uri = HttpClientApi.buildUri("https://planifium-api.onrender.com/api/v1/courses/" + CourseId, params);
        System.out.println("➡️ [Service] Appel API : " + uri);
        try{
            Course course = clientApi.get(uri, Course.class);
            return Optional.of(course);
        }catch (RuntimeException e){
            return Optional.empty();
        }
        
    }
}
