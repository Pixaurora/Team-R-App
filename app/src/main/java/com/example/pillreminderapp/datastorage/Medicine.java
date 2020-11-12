package com.example.pillreminderapp.datastorage;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine_table")
public class Medicine
{

    @PrimaryKey(autoGenerate = true)
    private int ID;

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


    public Medicine(int ID, String name, String type, String timeTaken)
    {
        this.ID = ID;
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


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String pill)
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
