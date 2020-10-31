package com.example.pillreminderapp;

import org.junit.Test;

import com.example.pillreminderapp.Medication;

import java.sql.Time;

import static org.junit.Assert.*;

public class TestMedClass {
    @Test
    public void timeWorks() {
        Time time = new Time(10, 10, 10);

        Medication medication = new Medication("Yeah", time);

        assertTrue(medication.time_taken == time);

    }
}