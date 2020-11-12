package com.example.pillreminderapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Medicine medicine);

    @Query("DELETE FROM medicine_table")
    void deleteAll();

    @Query("SELECT * FROM medicine_table ORDER BY ID asc")
    List<Medicine> getAllMedicines();
}
