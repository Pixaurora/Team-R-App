package com.example.pillreminderapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeStringConverter {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String TimeToString(LocalTime time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        return df.format(time);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalTime StringToTime(String timeString) {
        LocalTime time = LocalTime.parse(timeString);
        return time;
    }
}
