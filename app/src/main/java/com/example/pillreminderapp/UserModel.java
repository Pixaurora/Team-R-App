package com.example.pillreminderapp;

public class UserModel
{
    private String name;
    private String pill;
    private String timeTaken;

    //constructor


    public UserModel(String name, String pill, String timeTaken)
    {
        this.name = name;
        this.pill = pill;
        this.timeTaken = timeTaken;
    }

    //toString

    @Override
    public String toString()
    {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", pill='" + pill + '\'' +
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
        return pill;
    }

    public void setPill(String pill)
    {
        this.pill = pill;
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
