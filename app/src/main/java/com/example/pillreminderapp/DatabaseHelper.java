package com.example.pillreminderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
*Class helps with using SQLite open helper and creating a database
* Made by: Deven Sharma
* Edited by: Rina Shaw
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Medications.db";
    public static final String TABLE_NAME = "Medications_Table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Pill";
    public static final String COL_3 = "Time_Taken";

    //Constructor for database, uses super to help construct
    public DatabaseHelper(@Nullable Context context )
    {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
}
    //method that fires when database is created, helps with creating tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createDataStatement = "CREATE TABLE " + TABLE_NAME + " " + "(ID integer PRIMARY KEY AUTOINCREMENT, " + COL_1 + " TEXT, " + COL_2 +" TEXT, " + COL_3 + " TEXT )" ;
        db.execSQL(createDataStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    //adds one more column to the database table
    public boolean addOne(Medicine model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, model.getName());
        cv.put(COL_2, model.getPill());
        cv.put(COL_3, model.getTimeTaken());
        final long insert = db.insert(TABLE_NAME, null, cv);
        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    //gets a result set to return all records
    public List<Medicine> getEveryone()
    {
        List<Medicine> returnList = new ArrayList<>();

        String query = "SELECT Name, Pill, Time_Taken FROM Medications_Table";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //loop through results if they exist, creating usermodel's to put in array list
        if(cursor.moveToFirst())
        {
            do
            {
                String userName = cursor.getString(0);
                String pillName = cursor.getString(1);
                String timeTaken = cursor.getString(2);

                Medicine model = new Medicine(userName, pillName, timeTaken);
                returnList.add(model);
            } while(cursor.moveToFirst());
        }
        else
        {
            //Nothing is added since it's null
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public Medicine getOneByID(int ID) {
        String query = "SELECT Name, Pill, Time_Taken FROM Medications_Table";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String userName = cursor.getString(0);
        String pillName = cursor.getString(1);
        String timeTaken = cursor.getString(2);

        Medicine model = new Medicine(userName, pillName, timeTaken);

        return model;

    }
}
