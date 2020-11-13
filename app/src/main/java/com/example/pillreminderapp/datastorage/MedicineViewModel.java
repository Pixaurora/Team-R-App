package com.example.pillreminderapp.datastorage;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicineViewModel extends AndroidViewModel {

    private MedicineRepository mRepository;

    private final LiveData<List<Medicine>> mAllMedicines;

    public MedicineViewModel (Application application) {
        super(application);
        mRepository = new MedicineRepository(application);
        mAllMedicines = mRepository.getAllMedicines();
    }

    public LiveData<List<Medicine>> getAllMedicines() { return mAllMedicines; }

    public void insert(Medicine Medicine) { mRepository.insert(Medicine); }
}
