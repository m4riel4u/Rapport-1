package com.diro.ift2255.service;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.Schedule;
import com.diro.ift2255.model.EligibilityResult;
import com.diro.ift2255.util.HttpClientApi;
import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

public class CourseService {
    private final HttpClientApi clientApi;
    private static final String BASE_URL = "https://planifium-api.onrender.com/api/v1/courses";
    private final Map<String, String[]> csvCache = new HashMap<>();
    private static final String CSV_PATH ="src/main/java/com/diro/ift2255/historique_cours_prog_117510.csv";

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
     private String computeClassDifficulty(double score) {

        if (score >= 0 && score <= 1.0) return "5 (Très difficile)";
        if (score > 1.0 && score <= 2.0) return "4 (Difficile)";
        if (score > 2.0 && score <= 3.0) return "3 (Moyen)";
        if (score > 3.0 && score <= 4.0) return "2 (Facile)";
        if (score > 4.0 && score <= 5.0) return "1 (Très facile)";

        return "N/A"; 
    }
    /**Fetch course details */
    public Optional<Course> getCompleteCourse(String courseId) {
        Map<String, String> params = Map.of("complete", "true", "include_schedule", "true");
        URI uri = HttpClientApi.buildUri(BASE_URL + "/" + courseId, params);

        try {
            Course course = clientApi.get(uri, Course.class);

            // AJOUT CSV
            loadCsvIfNeeded();

            String[] csv = csvCache.get(course.getId());
            if (csv != null) {
                String average = csv[2];
            double difficultyScore = Double.parseDouble(csv[3]);

            course.setClass_average(average);
            course.setDifficulty_score(difficultyScore);

            // NOUVEL ATTRIBUT DÉRIVÉ
            String classDifficulty = computeClassDifficulty(difficultyScore);
            course.setClass_difficulty(classDifficulty);

            System.out.println(
                " | course=" + course.getId() +
                " | score=" + difficultyScore +
                " | difficulté=" + classDifficulty + "/5"
            );
        }

        return Optional.of(course);

        } catch (RuntimeException e) {
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
        try{
            Course[] courses = clientApi.get(uri, Course[].class);
            System.out.println(">>> réponse API OK : " + courses);
            return java.util.Arrays.asList(courses);

        }catch (RuntimeException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
        
    }

    //Fetch les cours d'un programme à un trimestre donné*/
    public List<Course> getCoursesByProgramWithSchedule(String program, boolean includeSchedule, String semester) {
        System.out.println(">>> getCoursesByProgramWithSchedule SERVICE CALLED");
        System.out.println(">>> program reçu = " + program);
        System.out.println(">>> includeSchedule = " + includeSchedule + ", semester = " + semester);

        //  Récupérer le programme pour avoir la liste des cours
        List<Course> programs = getCourseByProgram(program);
        System.out.println(">>> Réponse API getCourseByProgram OK, programmes récupérés = " + programs.size());

        if (programs.isEmpty()) {
            System.out.println(">>> Aucun programme trouvé pour " + program);
            return Collections.emptyList();
        }

        // Extraire tous les sigles de tous les blocs de tous les segments
        List<String> siglesList = new ArrayList<>();

    String term = semesterToTerm(semester);
    System.out.println(">>> Filtrage OBLIGATOIRE par terme = " + term);

    for (Course prog : programs) {

        if (prog.getCourses() == null || prog.getCourses().isEmpty()) {
            System.out.println(" Programme sans cours");
            continue;
        }

        for (String sigle : prog.getCourses()) {

            System.out.println(">>> Analyse du cours " + sigle);

            Optional<Course> optCourse = getCourseById(sigle, null);

            if (optCourse.isEmpty()) {
                System.out.println(" Cours introuvable: " + sigle);
                continue;
            }

            Course course = optCourse.get();
            Map<String, Boolean> terms = course.getAvailable_terms();

            if (terms == null) {
                System.out.println(" available_terms absent pour " + sigle);
                continue;
            }

            Boolean offered = terms.get(term);

            if (Boolean.TRUE.equals(offered)) {
                siglesList.add(sigle);
                System.out.println("AJOUTÉ (" + term + "=true) : " + sigle);
            } else {
                System.out.println(" REJETÉ (" + term + "=false) : " + sigle);
            }
        }
    }

System.out.println(">>> Total sigles retenus = " + siglesList.size());


    if (siglesList.isEmpty()) {
        System.out.println(">>> Aucun cours trouvé dans le programme " + program);
        return Collections.emptyList();
    }

    //  Construire la chaîne de sigles séparés par des virgules
    String sigles = String.join(",", siglesList);
    System.out.println(">>> Sigles à envoyer à l'API courses: " + sigles);

    //  Construire les paramètres de query pour l'API courses
    Map<String, String> params = new HashMap<>();
    params.put("courses_sigle", sigles);
    if (includeSchedule) {
        params.put("include_schedule", "true");
    }
    if (semester != null && !semester.isEmpty()) {
        params.put("schedule_semester", semester.toLowerCase());
    }

    // Construire l'URI finale
    URI uri = HttpClientApi.buildUri("https://planifium-api.onrender.com/api/v1/courses", params);
    System.out.println(">>> URL finale pour l'API courses: " + uri);

    //  Appeler l'API courses
    try {
        Course[] coursesWithSchedule = clientApi.get(uri, Course[].class);
        System.out.println(">>> Courses récupérés = " + coursesWithSchedule.length);

        List<Course> courseList = Arrays.asList(coursesWithSchedule);

        //  Retourner les cours, avec horaires si demandé
        if (includeSchedule) {
            for (Course c : courseList) {
                System.out.println("Cours: " + c.getId() + " - " + c.getName());
            }
        }

        return courseList;

        } catch (RuntimeException e) {
            System.out.println(">>> ERREUR API courses: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
  private String semesterToTerm(String semester) {
    if (semester == null || semester.isBlank()) {
        throw new IllegalArgumentException("Semester obligatoire pour le filtrage");
    }

    switch (Character.toUpperCase(semester.charAt(0))) {
        case 'H': return "winter";
        case 'A': return "autumn";
        case 'E': return "summer";
        default:
            throw new IllegalArgumentException("Semester invalide: " + semester);
        }
    }
    public EligibilityResult checkEligibility(List<String> completed, String target) {

        System.out.println(">>> checkEligibility SERVICE");
        System.out.println("Target : " + target);

        Optional<Course> courseOpt = getCourseById(target, null);

        if (courseOpt.isEmpty()) {
            return new EligibilityResult(false, List.of("Cours introuvable"));
        }

        Course course = courseOpt.get();
        List<String> prerequisites = course.getPrerequisite_courses();

        if (prerequisites == null || prerequisites.isEmpty()) {
            return new EligibilityResult(true, List.of());
        }

        List<String> missing = prerequisites.stream()
                .filter(pr -> !completed.contains(pr))
                .toList();

        return new EligibilityResult(missing.isEmpty(), missing);
    }
    private void loadCsvIfNeeded() {
    if (!csvCache.isEmpty()) {
        System.out.println(" CSV déjà chargé");
        return;
    }

    System.out.println(" Chargement du CSV depuis resources");

    try (InputStream is = getClass()
            .getClassLoader()
            .getResourceAsStream("historique_cours_prog_117510.csv")) {

        if (is == null) {
            throw new RuntimeException(" CSV introuvable dans src/main/resources");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        boolean first = true;

        while ((line = reader.readLine()) != null) {

            if (first) { // skip header
                first = false;
                continue;
            }

            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",", -1);

            if (parts.length < 4) {
                System.out.println(" Ligne CSV invalide ignorée : " + line);
                continue;
            }

            String sigle = parts[0].trim();

            csvCache.put(sigle, parts);

            System.out.println(
                "CSV → " + sigle +
                " | moyenne=" + parts[2] +
                " | difficulté=" + parts[3]
            );
        }

        System.out.println("CSV chargé : " + csvCache.size() + " cours");

    } catch (Exception e) {
        System.err.println(" Erreur chargement CSV");
        e.printStackTrace();
    }
}
   

}
