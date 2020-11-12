package com.example.pillreminderapp.datastorage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Medicine.class}, version = 1, exportSchema = false)
public abstract class MedicineRoomDatabase extends RoomDatabase {

    public abstract MedicineDao MedicineDao();

    private static volatile MedicineRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MedicineRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedicineRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MedicineRoomDatabase.class, "Medicine_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

