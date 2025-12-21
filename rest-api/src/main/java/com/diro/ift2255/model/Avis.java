package com.diro.ift2255.model;

public class Avis {
    String cours;
    int note;
    String utilisateur;
    String message;
    String lien;
    public Avis(){
        
    }
    public Avis(String cours, int note, String utilisateur, String message, String lien){
        this.cours = cours;
        this.note = note;
        this.utilisateur = utilisateur;
        this.message = message;
        this.lien = lien;
    }
        public String getCours() {return cours;}
        public void setCours(String cours) {this.cours = cours;}

        public int getNote() {return note;}
        public void setNote(int note) {this.note = note;}

        public String getMessage() {return message;}
        public void setMessage(String message) {this.message = message;}

        public String getUtilisateur() {return utilisateur;}
        public void setUtilisateur(String utilisateur) {this.utilisateur = utilisateur;}

        public String getLien() {return lien;}
        public void setLien(String lien) {this.lien = lien;}
    
    
}
