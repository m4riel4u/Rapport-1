package com.diro.ift2255.service;

import com.diro.ift2255.model.Avis;
import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.Schedule;
import com.diro.ift2255.model.User;
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

/**
 * La classe {@code CourseService} fournit des services pour gérer les cours, 
 * en particulier pour récupérer des informations sur les cours, 
 * vérifier l'éligibilité d'un utilisateur pour un cours donné, 
 * et organiser les données des cours.
 * 
 * Cette classe interagit avec une API externe pour obtenir des données sur les cours.
 */
public class CourseService {

    /**
     * Le client API utilisé pour effectuer les requêtes HTTP vers l'API externe.
     */
    private final HttpClientApi clientApi;

    /**
     * L'URL de base de l'API pour récupérer les informations sur les cours.
     */
    private static final String BASE_URL = "https://planifium-api.onrender.com/api/v1/courses";

    /**
     * Un cache pour stocker les données CSV des cours.
     */
    private final Map<String, String[]> csvCache = new HashMap<>();

    /**
     * Le chemin du fichier CSV contenant des informations supplémentaires sur les cours.
     */
    private static final String CSV_PATH ="src/main/java/com/diro/ift2255/historique_cours_prog_117510.csv";

    /**
     * Un cache pour stocker les avis des utilisateurs sur les cours.
     */
    private final Map<String, List<Avis>> avisParCours = new HashMap<>();
    

    /**
     * Constructeur de la classe {@code CourseService}.
     * @param clientApi Le client API utilisé pour effectuer les requêtes HTTP.
     */
    public CourseService(HttpClientApi clientApi) {
        this.clientApi = clientApi;
    }

    /**
     * Récupère tous les cours disponibles.
     * @param queryParams Les paramètres de la requête.
     * @return Une liste de tous les cours.
     */
    public List<Course> getAllCourses(Map<String, String> queryParams) {
        Map<String, String> params = (queryParams == null) ? Collections.emptyMap() : queryParams;

        URI uri = HttpClientApi.buildUri(BASE_URL, params);
        List<Course> courses = clientApi.get(uri, new TypeReference<List<Course>>() {});

        return courses;
    }

    /**
     * Récupère un cours par son identifiant (ID).
     * @param courseId L'ID du cours à récupérer.
     * @return Un objet {@code Optional} contenant le cours, s'il est trouvé.
     */
    public Optional<Course> getCourseById(String courseId) {
        return getCourseById(courseId, null);
    }

    /**
     * Récupère un cours par son identifiant (ID) avec des paramètres de requête optionnels.
     * @param courseId L'ID du cours à récupérer.
     * @param queryParams Les paramètres de la requête.
     * @return Un objet {@code Optional} contenant le cours, s'il est trouvé.
     */
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
    
    /**
     * Recherche les cours selon la requête.
     * @param query La chaîne de recherche pour filtrer les cours par leur ID ou nom.
     * @return Une liste de cours filtrés en fonction de la requête.
     */
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

    /**
     * Calcule la difficulté d'un cours en fonction de sa note de difficulté.
     * @param score La note de difficulté du cours (entre 0 et 5).
     * @return La description de la difficulté du cours.
     */
    private String computeClassDifficulty(double score) {

        if (score >= 0 && score <= 1.0) return "5 (Très difficile)";
        if (score > 1.0 && score <= 2.0) return "4 (Difficile)";
        if (score > 2.0 && score <= 3.0) return "3 (Moyen)";
        if (score > 3.0 && score <= 4.0) return "2 (Facile)";
        if (score > 4.0 && score <= 5.0) return "1 (Très facile)";

        return "N/A"; 
    }
    
    /**
     * Récupère les détails complets d'un cours.
     * @param courseId L'ID du cours à récupérer.
     * @return Un objet {@code Optional} contenant le cours complet, s'il est trouvé.
     */
    public Optional<Course> getCompleteCourse(String courseId) {
        loadAvisIfNeeded();
        Map<String, String> params = Map.of("complete", "true", "include_schedule", "true");
        URI uri = HttpClientApi.buildUri(BASE_URL + "/" + courseId, params);

        try {
            Course course = clientApi.get(uri, Course.class);
            //Ajout avis.log
            List<Avis> avisCours = avisParCours.get(course.getId());

            if (avisCours != null && !avisCours.isEmpty()) {

                double moyenne = avisCours.stream()
                    .mapToInt(Avis::getNote)
                    .average()
                    .orElse(0);

                String messages = avisCours.stream()
                    .map(Avis::getMessage)
                    .distinct()
                    .reduce("", (a, b) -> a + "• " + b + "\n");

                course.setNote_etudiant(String.format("%.2f", moyenne));
                course.setAvis(messages);
            } else {
                course.setNote_etudiant("—");
                course.setAvis("Aucun avis");
            }
            // AJOUT CSV
            loadCsvIfNeeded();

            String[] csv = csvCache.get(course.getId());
            if (csv != null) {
                String average = csv[2];
                course.setClass_average(average);

                String difficultyRaw = csv[3];

                if (difficultyRaw == null || difficultyRaw.isBlank()) {
                    course.setDifficulty_score(null);
                    course.setClass_difficulty("—");
                    System.out.println(
                        " | course=" + course.getId() +
                        " | score=— | difficulté=—"
                    );
                } else {
                    double difficultyScore = Double.parseDouble(difficultyRaw);
                    String classDifficulty = computeClassDifficulty(difficultyScore);
                    course.setDifficulty_score(difficultyScore);
                    course.setClass_difficulty(classDifficulty);
                


            System.out.println(
                " | course=" + course.getId() +
                " | score=" + difficultyScore +
                " | difficulté=" + classDifficulty + "/5"
            );
                }
        }
        return Optional.of(course);

        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    //Méthode pour formater toutes les info données avec les horaires 

    /**
     * Restructure les informations des cours et les horaires sous un format organisé.
     * @param course Le cours dont les informations doivent être restructurées.
     * @return Une liste de cartes contenant les informations structurées des cours.
     */
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

    /**
     * Formate les informations d'un examen (intra ou final) sous un format lisible.
     * @param volet Le volet contenant les informations sur l'examen.
     * @return Les informations de l'examen.
     */
    private String formatExam(Schedule.Volet volet) {
        if (volet.activities == null || volet.activities.isEmpty()) {
            return "Non spécifié";
        }

        Schedule.Activity act = volet.activities.get(0);

        return String.join(",", act.days)
                + " " + act.start_time + "-" + act.end_time
                + " (" + (act.room != null ? act.room : "??") + ")";
    }
    
    /**
     * Récupère les cours d’un programme.
     * @param program Le sigle du programme
     * @return La liste de {@link Course} du programme
     */
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

    /**
     * Récupère les cours d’un programme pour un semestre donné et optionnellement avec horaires.
     * @param program Le sigle du programme
     * @param includeSchedule Inclure ou non les horaires
     * @param semester Semestre (ex: "H2025")
     * @return La liste de {@link Course} filtrés
     */
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

    /**
     * Convertit un semestre sous forme de code (ex: "H2025") en un terme valide pour l'API.
     * @param semester Le semestre sous forme de code (ex: "H2025").
     * @return Le terme correspondant (ex: "winter", "autumn", "summer").
     * @throws IllegalArgumentException Si le semestre est invalide.
     */
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

    /**
     * Vérifie l'éligibilité d'un utilisateur à un cours donné en fonction des cours qu'il a déjà complétés.
     * @param completed La liste des cours que l'utilisateur a complétés.
     * @param target Le sigle du cours cible à vérifier.
     * @return {@link EligibilityResult} qui indique si l'utilisateur est éligible ou non, 
     *         et la liste des prérequis manquants si applicable.
     */
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

        // Normaliser les cours complétés
        if (completed == null) completed = List.of();
        List<String> normalizedCompleted = completed.stream()
                .map(c -> c.trim().toUpperCase())
                .toList();

        // Normaliser et vérifier les prérequis
        List<String> missing = prerequisites.stream()
                .map(p -> p.trim().toUpperCase())
                .filter(p -> !normalizedCompleted.contains(p))
                .toList();

        return new EligibilityResult(missing.isEmpty(), missing);
    }

    /**
     * Charge les données CSV nécessaires si elles ne sont pas déjà chargées.
     */
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

    /**
     * Charge les avis des utilisateurs à partir d'un fichier de log si nécessaire.
     */
    private void loadAvisIfNeeded(){
    if(!avisParCours.isEmpty())return;

    try (InputStream is = getClass().getClassLoader().getResourceAsStream("avis.log")){
        if (is == null){
            System.err.println("avis.log introuvable");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        Avis avis = null;

        while ((line = br.readLine()) != null){
            if (line.startsWith("Cours :")){
                avis = new Avis();
                avis.setCours(line.substring(7).trim().toUpperCase());
            }
            else if (line.startsWith("Note :")) {
                avis.setNote(Integer.parseInt(line.substring(6).trim()));
            } 
            else if (line.startsWith("Utilisateur :")) {
                avis.setUtilisateur(line.substring(12).trim());
            } 
            else if (line.startsWith("Message :")) {
                avis.setMessage(line.substring(9).trim() + "<br>");
            } 
            else if (line.startsWith("Lien du message :")) {
                avis.setLien(line.substring(17).trim());
                avisParCours.computeIfAbsent(avis.getCours(), k -> new ArrayList<>()).add(avis);
            }
        }
        System.out.println("Avis chargés : "+ avisParCours.size() + "cours");
    }catch (Exception e){
            e.printStackTrace();
    }
    }

    /**
     * Vérifie l'éligibilité d'un utilisateur à un cours donné en fonction des cours qu'il a déjà complétés.
     * Cette méthode utilise les données de l'utilisateur {@code user}.
     * @param user L'utilisateur à vérifier.
     * @param courseId Le sigle du cours cible à vérifier.
     * @return {@link EligibilityResult} indiquant l'éligibilité de l'utilisateur.
     */
    public EligibilityResult checkEligibilityForUser(User user, String courseId) {
        return checkEligibility(
            user.getCompletedCourses(),
            courseId
        );
    }

}
