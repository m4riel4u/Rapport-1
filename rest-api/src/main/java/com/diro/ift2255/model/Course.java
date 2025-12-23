package com.diro.ift2255.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List; 
import java.util.Map;

/**
 * La classe {@code Course} représente un cours universitaire avec ses propriétés
 * telles que l'ID, le nom, la description, les prérequis, les cours équivalents, les horaires,
 * ainsi que des informations supplémentaires telles que la difficulté, la note de l'étudiant, et bien d'autres.
 * 
 * Cette classe est utilisée pour stocker et manipuler les informations liées à un cours dans l'application.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    /**
     * L'ID unique du cours.
     */
    private String id;

    /**
     * Le nom du cours.
     */
    private String name;

    /**
     * La description détaillée du cours.
     */
    private String description;

    /**
     * Le texte décrivant les prérequis nécessaires pour suivre ce cours.
     */
    private String requirement_text;

    /**
     * La moyenne de la classe pour ce cours.
     */
    private String class_average;

    /**
     * La difficulté générale du cours, sur une échelle prédéfinie.
     */
    private String class_difficulty;

    /**
     * Les avis concernant le cours.
     */
    private String avis;

    /**
     * La note de l'étudiant pour ce cours.
     */
    private String note_etudiant;

    /**
     * Le cycle d'étude auquel ce cours appartient.
     */
    private String cycle;

    /**
     * Les périodes disponibles pour ce cours.
     */
    private Map<String, Boolean> available_periods;

    /**
     * Les termes disponibles pour ce cours.
     */
    private Map<String, Boolean> available_terms;

    /**
     * Le nombre de crédits associés à ce cours.
     */
    private double credits;

    /**
     * Le score de difficulté du cours.
     */
    private Double difficulty_score;

    /**
     * La liste des horaires du cours.
     */
    private List<Schedule> schedules;

    /**
     * La liste des cours prérequis nécessaires avant de suivre ce cours.
     */
    private List<String> prerequisite_courses;

    /**
     * La liste des cours équivalents à ce cours.
     */
    private List<String> equivalent_courses;

    /**
     * La liste des cours concomitants à ce cours.
     */
    private List<String> concomitant_courses;

    /**
     * La liste des autres cours associés ou recommandés par ce cours.
     */
    private List<String> courses;

    /**
     * Constructeur par défaut.
     * 
     * Crée une instance vide d'un objet {@code Course}.
     */
    public Course() {
        // Constructeur par défaut
    }

    /**
     * Constructeur principal permettant de créer un cours avec toutes ses informations.
     * 
     * @param id L'ID unique du cours.
     * @param name Le nom du cours.
     * @param desc La description détaillée du cours.
     * @param peri Les périodes disponibles pour ce cours.
     * @param req Le texte des prérequis nécessaires pour suivre ce cours.
     * @param cred Le nombre de crédits associés au cours.
     * @param sche La liste des horaires pour ce cours.
     * @param pre La liste des cours prérequis.
     * @param terms Les termes disponibles pour ce cours.
     * @param equi La liste des cours équivalents.
     * @param conco La liste des cours concomitants.
     * @param courses La liste des autres cours associés à ce cours.
     * @param difficulty_score Le score de difficulté du cours.
     * @param class_average La moyenne de la classe pour ce cours.
     * @param class_difficulty La difficulté générale du cours.
     * @param avis Les avis concernant le cours.
     * @param note_etudiant La note de l'étudiant pour ce cours.
     * @param cycle Le cycle d'études auquel appartient ce cours.
     */
    public Course(String id, String name, String desc, Map<String, Boolean> peri,
                String req, double cred, List<Schedule> sche, 
                List<String> pre, Map<String, Boolean> terms, 
                List<String> equi, List<String> conco, List<String> courses,
                Double difficulty_score, String class_average, String class_difficulty,
                String avis, String note_etudiant, String cycle) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.available_periods = peri;
        this.credits = cred;
        this.schedules = sche;
        this.prerequisite_courses = pre;
        this.available_terms = terms;
        this.equivalent_courses = equi;
        this.concomitant_courses = conco;
        this.requirement_text = req;
        this.courses = courses;
        this.class_average = class_average;
        this.difficulty_score = difficulty_score;
        this.class_difficulty = class_difficulty;
        this.avis = avis;
        this.note_etudiant = note_etudiant; 
        this.cycle = cycle;
    }

    /**
     * Obtient l'ID du cours.
     * @return L'ID du cours.
     */
    public String getId() { return id; }

    /**
     * Définit l'ID du cours.
     * @param id L'ID du cours à définir.
     */
    public void setId(String id) { this.id = id; }

    /**
     * Obtient le nom du cours.
     * @return Le nom du cours.
     */
    public String getName() { return name; }

    /**
     * Définit le nom du cours.
     * @param name Le nom du cours à définir.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Obtient la description du cours.
     * @return La description du cours.
     */
    public String getDescription() { return description; }

    /**
     * Définit la description du cours.
     * @param description La description du cours à définir.
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Obtient les prérequis nécessaires pour suivre ce cours.
     * @return Les prérequis.
     */
    public String getRequirement_text() { return requirement_text; }

    /**
     * Définit les prérequis nécessaires pour suivre ce cours.
     * @param requirement_text Les prérequis à définir.
     */
    public void setRequirement_text(String requirement_text) { this.requirement_text = requirement_text; }

    /**
     * Obtient les périodes disponibles pour ce cours.
     * @return Les périodes disponibles sous forme de clé-valeur.
     */
    public Map<String, Boolean> getAvailable_periods() { return available_periods; }

    /**
     * Définit les périodes disponibles pour ce cours.
     * @param available_periods Les périodes disponibles à définir.
     */
    public void setAvailable_periods(Map<String, Boolean> available_periods) { this.available_periods = available_periods; }

    /**
     * Obtient le nombre de crédits associés à ce cours.
     * @return Le nombre de crédits.
     */
    public double getCredits() { return credits; }

    /**
     * Définit le nombre de crédits associés à ce cours.
     * @param credits Le nombre de crédits à définir.
     */
    public void setCredits(double credits) { this.credits = credits; }

    /**
     * Obtient la liste des horaires pour ce cours.
     * @return La liste des horaires du cours.
     */
    public List<Schedule> getSchedules() { return schedules; }

    /**
     * Définit la liste des horaires pour ce cours.
     * @param schedules La liste des horaires à définir.
     */
    public void setSchedules(List<Schedule> schedules) { this.schedules = schedules; }

    /**
     * Obtient la liste des cours prérequis pour ce cours.
     * @return La liste des cours prérequis.
     */
    public List<String> getPrerequisite_courses() { return prerequisite_courses; }

    /**
     * Définit la liste des cours prérequis pour ce cours.
     * @param prerequisite_courses La liste des cours prérequis à définir.
     */
    public void setPrerequisite_courses(List<String> prerequisite_courses) { this.prerequisite_courses = prerequisite_courses; }

    /**
     * Obtient les sessions disponibles pour ce cours.
     * @return Les sessions disponibles sous forme de clé-valeur.
     */
    public Map<String, Boolean> getAvailable_terms() { return available_terms; }

    /**
     * Définit les sessions disponibles pour ce cours.
     * 
     * @param available_terms Les sessions disponibles à définir.
     */
    public void setAvailable_terms(Map<String, Boolean> available_terms) { this.available_terms = available_terms; }

    /**
     * Obtient la liste des cours équivalents à ce cours.
     * @return La liste des cours équivalents.
     */
    public List<String> getEquivalent_courses() { return equivalent_courses; }

    /**
     * Définit la liste des cours équivalents à ce cours.
     * @param equivalent_courses La liste des cours équivalents à définir.
     */
    public void setEquivalent_courses(List<String> equivalent_courses) { this.equivalent_courses = equivalent_courses; }

    /**
     * Obtient la liste des cours en chevauchement à ce cours.
     * @return La liste des cours en chevauchement.
     */
    public List<String> getConcomitant_courses() { return concomitant_courses; }

    /**
     * Définit la liste des cours en chevauchement à ce cours.
     * @param concomitant_courses La liste des cours en chevauchement à définir.
     */
    public void setConcomitant_courses(List<String> concomitant_courses) { this.concomitant_courses = concomitant_courses; }

    /**
     * Obtient la liste des autres cours associés à ce cours.
     * @return La liste des cours associés.
     */
    public List<String> getCourses() { return courses; }

    /**
     * Définit la liste des autres cours associés à ce cours.
     * @param courses La liste des cours associés à définir.
     */
    public void setCourses(List<String> courses) { this.courses = courses; }

    /**
     * Obtient la moyenne de la classe pour ce cours.
     * 
     * @return La moyenne de la classe.
     */
    public String getClass_average() { return class_average; }

    /**
     * Définit la moyenne de la classe pour ce cours.
     * @param class_average La moyenne de la classe à définir.
     */
    public void setClass_average(String class_average) { this.class_average = class_average; }

    /**
     * Obtient le score de difficulté du cours.
     * @return Le score de difficulté du cours.
     */
    public Double getDifficulty_score() { return difficulty_score; }

    /**
     * Définit le score de difficulté du cours.
     * @param difficulty_score Le score de difficulté à définir.
     */
    public void setDifficulty_score(Double difficulty_score) { this.difficulty_score = difficulty_score; }

    /**
     * Obtient la difficulté générale du cours.
     * @return La difficulté générale du cours.
     */
    public String getClass_difficulty() { return class_difficulty; }

    /**
     * Définit la difficulté générale du cours.
     * @param classDifficulty La difficulté générale à définir.
     */
    public void setClass_difficulty(String classDifficulty) { this.class_difficulty = classDifficulty; }

    /**
     * Obtient les avis concernant ce cours.
     * @return Les avis associés au cours.
     */
    public String getAvis() { return avis; }

    /**
     * Définit les avis concernant ce cours.
     * @param avis Les avis à définir pour ce cours.
     */
    public void setAvis(String avis) { this.avis = avis; }

    /**
     * Obtient la note de l'étudiant pour ce cours.
     * @return La note de l'étudiant.
     */
    public String getNote_etudiant() { return note_etudiant; }

    /**
     * Définit la note de l'étudiant pour ce cours.
     * @param note_etudiant La note de l'étudiant à définir.
     */
    public void setNote_etudiant(String note_etudiant) { this.note_etudiant = note_etudiant; }

    /**
     * Obtient le cycle d'études auquel appartient ce cours.
     * @return Le cycle d'études du cours.
     */
    public String getCycle() { return cycle; }

    /**
     * Définit le cycle d'études auquel appartient ce cours.
     * @param cycle Le cycle d'études à définir.
     */
    public void setCycle(String cycle) { this.cycle = cycle; }
}