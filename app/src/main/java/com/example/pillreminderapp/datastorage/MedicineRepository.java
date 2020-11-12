package com.example.pillreminderapp.datastorage;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class MedicineRepository {

    private MedicineDao mMedicineDao;
    private LiveData<List<Medicine>> mAllMedicines;

    // Note that in order to unit test the MedicineRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    MedicineRepository(Application application) {
        MedicineRoomDatabase db = MedicineRoomDatabase.getDatabase(application);
        mMedicineDao = db.MedicineDao();
        mAllMedicines = mMedicineDao.getAllMedicines();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Medicine>> getAllMedicines() {
        return mAllMedicines;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Medicine Medicine) {
        MedicineRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMedicineDao.insert(Medicine);
        });
    }
}
