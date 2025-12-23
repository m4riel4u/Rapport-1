package com.diro.ift2255.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * La classe {@code User} représente un utilisateur dans l'application. 
 * Un utilisateur possède des informations telles que son identifiant, son nom, son email, son cycle d'études, et une liste de cours qu'il a complétés.
 * 
 * Cette classe est utilisée pour gérer les informations personnelles et académiques d'un utilisateur.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    /**
     * L'identifiant unique de l'utilisateur.
     */
    private int id;

    /**
     * Le nom de l'utilisateur.
     */
    private String name;

    /**
     * Le courriel de l'utilisateur.
     */
    private String email;

    /**
     * Le cycle d'études de l'utilisateur.
     */
    private String cycle;

    /**
     * La liste des cours que l'utilisateur a complétés.
     */
    private List<String> completedCourses;


    /**
     * Constructeur par défaut de la classe {@code User}.
     * Ce constructeur est utilisé pour créer un objet {@code User} sans initialiser ses attributs.
     */
    public User() {}


    /**
     * Constructeur de la classe {@code User} qui permet d'initialiser tous les attributs de l'utilisateur.
     * 
     * @param id L'identifiant unique de l'utilisateur.
     * @param name Le nom de l'utilisateur.
     * @param email Le courriel de l'utilisateur.
     * @param cycle Le cycle d'études de l'utilisateur.
     * @param completedCourses La liste des cours complétés par l'utilisateur.
     */
    public User(int id, String name, String email, String cycle, List<String> completedCourses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cycle = cycle;
        this.completedCourses = completedCourses;
    }

    /**
     * Obtient l'identifiant de l'utilisateur.
     * @return L'identifiant de l'utilisateur.
     */
    public int getId() { return id; }

    /**
     * Définit l'identifiant de l'utilisateur.
     * @param id L'identifiant de l'utilisateur à définir.
     */
    public void setId(int id) { this.id = id; }


    /**
     * Obtient le nom de l'utilisateur.
     * @return Le nom de l'utilisateur.
     */
    public String getName() { return name; }

    /**
     * Définit le nom de l'utilisateur.
     * @param name Le nom de l'utilisateur à définir.
     */
    public void setName(String name) { this.name = name; }


    /**
     * Obtient le courriel de l'utilisateur.
     * 
     * @return Le courriel de l'utilisateur.
     */
    public String getEmail() { return email; }

    /**
     * Définit le courriel de l'utilisateur.
     * 
     * @param email Le courriel de l'utilisateur à définir.
     */
    public void setEmail(String email) { this.email = email; }


    /**
     * Obtient le cycle d'études de l'utilisateur.
     * @return Le cycle d'études de l'utilisateur.
     */
    public String getCycle() { return cycle; }

    /**
     * Définit le cycle d'études de l'utilisateur.
     * @param cycle Le cycle d'études de l'utilisateur à définir.
     */
    public void setCycle(String cycle) { this.cycle = cycle; }


    /**
     * Obtient la liste des cours que l'utilisateur a complétés.
     * @return La liste des cours complétés par l'utilisateur.
     */
    public List<String> getCompletedCourses() {return completedCourses;}

    /**
     * Définit la liste des cours que l'utilisateur a complétés.
     * @param completedCourses La liste des cours complétés à définir pour l'utilisateur.
     */
    public void setCompletedCourses(List<String> completedCourses) {this.completedCourses = completedCourses;}
}