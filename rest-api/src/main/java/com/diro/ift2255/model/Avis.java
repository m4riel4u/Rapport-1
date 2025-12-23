package com.diro.ift2255.model;

/**
 * La classe {@code Avis} représente un avis qu'un utilisateur peut laisser sur un cours.
 * Un avis comprend des informations telles que le nom du cours, la note, l'utilisateur qui a laissé l'avis, le message de l'avis et un lien éventuel lié à l'avis.
 * Cette classe contient des méthodes pour accéder et modifier ces informations.
 */
public class Avis {
    /**
     * Le nom du cours pour lequel l'avis est donné.
     */
    String cours;

    /**
     * La note attribuée au cours.
     */
    int note;

    /**
     * Le nom de l'utilisateur qui a laissé l'avis.
     */
    String utilisateur;

    /**
     * Le message de l'avis, décrivant l'expérience ou l'opinion de l'utilisateur.
     */
    String message;


    /**
     * Constructeur par défaut. Crée un objet {@code Avis} vide.
     */
    /**
     * Un lien éventuel fourni par l'utilisateur, pouvant rediriger vers plus d'informations ou des ressources supplémentaires.
     */
    String lien;

    /**
     * Constructeur par défaut. Crée un objet {@code Avis} vide.
     */
    public Avis(){
        
    }

    /**
     * Constructeur principal pour créer un avis avec toutes ses informations.
     * 
     * @param cours Le nom du cours pour lequel l'avis est donné.
     * @param note La note attribuée au cours.
     * @param utilisateur Le nom de l'utilisateur qui a donné l'avis.
     * @param message Le message de l'avis, décrivant l'expérience de l'utilisateur.
     * @param lien Un lien optionnel associé à l'avis.
     */
    public Avis(String cours, int note, String utilisateur, String message, String lien){
        this.cours = cours;
        this.note = note;
        this.utilisateur = utilisateur;
        this.message = message;
        this.lien = lien;
    }

        /**
        * Obtient le nom du cours pour lequel l'avis a été donné.
        * @return Le nom du cours.
        */
        public String getCours() {return cours;}

        /**
        * Définit le nom du cours pour lequel l'avis est donné.
        * @param cours Le nom du cours.
        */
        public void setCours(String cours) {this.cours = cours;}

        /**
        * Obtient la note attribuée au cours dans l'avis.
        * @return La note attribuée.
        */
        public int getNote() {return note;}

        /**
        * Définit la note attribuée au cours dans l'avis.
        * @param note La note à attribuer au cours.
        */
        public void setNote(int note) {this.note = note;}

        /**
        * Obtient le message de l'avis, qui décrit l'expérience de l'utilisateur.
        * @return Le message de l'avis.
        */
        public String getMessage() {return message;}

        /**
        * Définit le message de l'avis, qui décrit l'expérience de l'utilisateur.
        * @param message Le message à définir pour l'avis.
        */
        public void setMessage(String message) {this.message = message;}

        /**
        * Obtient le nom de l'utilisateur qui a laissé l'avis.
        * @return Le nom de l'utilisateur.
        */
        public String getUtilisateur() {return utilisateur;}

        /**
        * Définit le nom de l'utilisateur qui a laissé l'avis.
        * @param utilisateur Le nom de l'utilisateur à définir.
        */
        public void setUtilisateur(String utilisateur) {this.utilisateur = utilisateur;}

        /**
        * Obtient le lien associé à l'avis, qui peut pointer vers des informations supplémentaires.
        * @return Le lien associé à l'avis.
        */
        public String getLien() {return lien;}

        /**
        * Définit le lien associé à l'avis, qui peut pointer vers des informations supplémentaires.
        * @param lien Le lien à associer à l'avis.
        */
        public void setLien(String lien) {this.lien = lien;}
}