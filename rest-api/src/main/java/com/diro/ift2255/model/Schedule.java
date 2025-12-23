package com.diro.ift2255.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * La classe {@code Schedule} représente les horaires de cours, 
 * incluant les informations sur le sigle, le semestre, les sections et les activités de ce cours.
 * Elle contient également des informations détaillées sur les sections, les volets et les activités liées.
 * 
 * Cette classe est utilisée pour structurer les données récupérées sur l'horaire d'un cours.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {

    // informations sur les schedules

    /**
     * L'identifiant unique de l'emploi du temps.
     */
    public String _id;
    
    /**
     * Le sigle du cours auquel l'emploi du temps est associé.
     */
    public String sigle;

    /**
     * Le nom du cours associé à cet emploi du temps.
     */
    public String name;

    /**
     * Le semestre durant lequel ce cours est donné (par exemple, "Hiver 2023").
     */
    public String semester;

    /**
     * La date de la dernière récupération de l'emploi du temps.
     */
    public String fetch_date;

    /**
     * Le semestre sous forme d'entier (par exemple, 1 pour le premier semestre, 2 pour le deuxième semestre).
     */
    public int semester_int;

    /**
     * La liste des sections associées à cet emploi du temps. Une section représente un groupe d'étudiants inscrits à un cours.
     */
    public List<Section> sections;


    //informations sur les sections

    /**
     * La classe interne {@code Section} représente une section spécifique d'un cours, incluant des informations
     * comme le nom de la section, son numéro d'inscription, la capacité, les enseignants, et les volets associés.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Section {

        /**
        * Le nom de la section.
        */
        public String name;                 // A, B, A101, etc.

        /**
        * Le nombre d'inscription de la section.
        */
        public String number_inscription;

        /**
        * La capacité maximale d'étudiants pour cette section.
        */
        public String capacity;

        /**
        * La liste des enseignants assignés à cette section.
        */
        public List<String> teachers;

        /**
        * La liste des volets associés à cette section.
        */
        public List<Volet> volets;
    }


    // informations dans les volets

    /**
     * La classe interne {@code Volet} représente un volet d'un cours.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Volet {

        /**
        * Le nom du volet.
        */
        public String name;                 // TH, TP, Intra, Final

        /**
        * La liste des activités associées à ce volet.
        * Chaque activité représente un créneau horaire spécifique pour une partie du cours.
        */
        public List<Activity> activities;
    }


    //informations sur les activities => Vrai horaire des cours (temps) 

    /**
     * La classe interne {@code Activity} représente une activité spécifique dans le volet d'un cours.
     * Elle contient des informations sur les jours, les horaires, les dates, et le lieu où l'activité se déroule.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Activity {
        /**
        * La liste des jours de la semaine où l'activité se déroule.
        */
        public List<String> days;

        /**
        * L'heure de début de l'activité.
        */
        public String start_time;

        /**
        * L'heure de fin de l'activité.
        */
        public String end_time;

        /**
        * La date de début de l'activité.
        */
        public String start_date;

        /**
        * La date de fin de l'activité.
        */
        public String end_date;

        /**
        * Le campus où se déroule l'activité.
        */
        public String campus;

        /**
        * La lieu où se déroule l'activité.
        */
        public String place;

        /**
        * Le nom du pavillon où se déroule l'activité.
        */
        public String pavillon_name;

        /**
        * Le numéro de la salle où se déroule l'activité.
        */
        public String room;

        /**
        * Le mode d'enseignement de l'activité ("Présentiel", "Virtuel", etc.).
        */
        public String mode;
    }
}