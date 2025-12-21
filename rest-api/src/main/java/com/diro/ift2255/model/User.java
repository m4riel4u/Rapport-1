package com.diro.ift2255.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id;
    private String name;
    private String email;
    private String cycle; 
    private List<String> completedCourses;

    public User() {}

    public User(int id, String name, String email, String cycle, List<String> completedCourses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cycle = cycle;
        this.completedCourses = completedCourses;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCycle(){return cycle;}
    public void setCycle(String cycle){this.cycle = cycle;}

    public List<String> getCompletedCourses() {return completedCourses;}
    public void setCompletedCourses(List<String> completedCourses) {this.completedCourses = completedCourses;}
}

