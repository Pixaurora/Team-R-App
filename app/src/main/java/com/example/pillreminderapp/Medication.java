package com.example.pillreminderapp;

import java.sql.Time;

public class Medication {       ///this a class to model a medication object
    String name;
    Time time_taken;
    String dosage;

    public Medication(String name, Time time_taken) {
        this.name = name;
        this.time_taken = time_taken;
        this.dosage= dosage;
    }


    public String getName() {
        return name;
    }

    public Time getTime_taken() {
        return time_taken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime_taken(Time time_taken) {
        this.time_taken = time_taken;
    }

    public String getDosage(){return dosage;}

    public void setDosage(String aDosage){aDosage=dosage;}
}
