package com.example.pillreminderapp;

import org.junit.Test;

import java.time.LocalTime;

public class TestTime {
    @Test
    public void TimeWorks() {
        LocalTime time = TimeStringConverter.StringToTime("10:08");

        System.out.println(time);
    }
}
