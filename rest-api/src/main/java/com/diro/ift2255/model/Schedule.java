package com.diro.ift2255.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {
    public String day;
    public String start_time;
    public String end_time;
    public String room;
    
}
