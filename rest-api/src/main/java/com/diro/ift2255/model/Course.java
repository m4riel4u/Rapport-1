package com.diro.ift2255.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List; 
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    public Course(){}

    private String id;
    private String name;
    private String description;
    private String requirement_text;
    private String class_average;
    private String class_difficulty; 
    private String avis;
    private String note_etudiant; 
    private String cycle;

    private Map<String, Boolean> available_periods;
    private Map<String, Boolean> available_terms;

    private double credits;
    private Double difficulty_score;

    private List<Schedule> schedules;

    private List<String> prerequisite_courses;
    private List<String> equivalent_courses;
    private List<String> concomitant_courses;
    private List<String> courses;


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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String email) { this.description = email; }

    public Map<String, Boolean> getAvailable_periods() { return available_periods; }
    public void setAvailable_periods(Map<String, Boolean> available_periods) { this.available_periods = available_periods; }

    public String getRequirement_text() { return requirement_text; }
    public void setRequirement_text(String requirement_text) { this.requirement_text = requirement_text; }

    public double getCredits() { return credits; }
    public void setCredits(double credits) { this.credits = credits; }

    public List<Schedule> getSchedules() { return schedules; }
    public void setSchedules(List<Schedule> schedules) { this.schedules = schedules; }

    public List<String> getPrerequisite_courses() { return prerequisite_courses; }
    public void setPrerequisite_courses(List<String> prerequisites) { this.prerequisite_courses = prerequisites; }

    public Map<String, Boolean> getAvailable_terms() { return available_terms; }
    public void setAvailable_terms(Map<String, Boolean> available_terms) { this.available_terms = available_terms; }

    public List<String> getEquivalent_courses() { return equivalent_courses; }
    public void setEquivalent_courses(List<String> equivalent_courses) { this.equivalent_courses = equivalent_courses; }

    public List<String> getConcomitant_courses() { return concomitant_courses; }
    public void setConcomitant_courses(List<String> concomitant_courses) { this.concomitant_courses = concomitant_courses; }

    public List<String> getCourses() { return courses; }
    public void setCourses(List<String> courses) { this.courses = courses; }

    public String getClass_average() {return class_average;}
    public void setClass_average(String class_average) {this.class_average = class_average;}

    public Double getDifficulty_score() { return difficulty_score; }
    public void setDifficulty_score(Double difficulty_score) { this.difficulty_score = difficulty_score; }

    public String getClass_difficulty() {return class_difficulty;}
    public void setClass_difficulty(String classDifficulty) {this.class_difficulty = classDifficulty;}

    public String getAvis() {return avis;}
    public void setAvis(String avis) {this.avis = avis;}

    public String getNote_etudiant(){return note_etudiant;}
    public void setNote_etudiant(String note_etudiant) {this.note_etudiant = note_etudiant;}

    public String getCycle() {return cycle;}
    public void setCycle(String cycle) {this.cycle = cycle;}
}
