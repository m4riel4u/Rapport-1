package com.diro.ift2255.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {

    // informations sur les schedules
    public String _id;
    public String sigle;
    public String name;
    public String semester;
    public String fetch_date;
    public int semester_int;

    public List<Section> sections;

    //informations sur les sections
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Section {
        public String name;                 // A, B, A101, etc.
        public String number_inscription;
        public String capacity;
        public List<String> teachers;

        public List<Volet> volets;
    }

    // informations dans les volets
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Volet {
        public String name;                 // TH, TP, Intra, Final
        public List<Activity> activities;
    }

    //informations sur les activities => Vrai horaire des cours (temps) 
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Activity {
        public List<String> days;
        public String start_time;
        public String end_time;
        public String start_date;
        public String end_date;
        public String campus;
        public String place;
        public String pavillon_name;
        public String room;
        public String mode;
    }
}
