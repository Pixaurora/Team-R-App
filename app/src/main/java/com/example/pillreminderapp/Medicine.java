package com.example.pillreminderapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine_table")
public class Medicine
{

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "type")
    @NonNull
    private String type;

    @NonNull
    @ColumnInfo(name = "time_taken")
    private String timeTaken;

    //constructor


    public Medicine(String name, String type, String timeTaken)
    {
        this.name = name;
        this.type = type;
        this.timeTaken = timeTaken;
    }

    //toString

    @Override
    public String toString()
    {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", timeTaken='" + timeTaken + '\'' +
                '}';
    }


    //getter + setters

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPill()
    {
        return type;
    }

    public void setPill(String pill)
    {
        this.type = pill;
    }

    public String getTimeTaken()
    {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken)
    {
        this.timeTaken = timeTaken;
    }
}
