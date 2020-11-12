package com.example.pillreminderapp.datastorage;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pillreminderapp.datastorage.Medicine;

import java.util.List;

@Dao
public interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Medicine medicine);

    @Query("DELETE FROM medicine_table")
    void deleteAll();

    @Query("SELECT * FROM medicine_table ORDER BY ID asc")
    LiveData<List<Medicine>> getAllMedicines();
}
