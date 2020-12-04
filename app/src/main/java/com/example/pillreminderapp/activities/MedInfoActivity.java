package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.example.pillreminderapp.R;
import com.example.pillreminderapp.datastorage.Medicine;
import com.example.pillreminderapp.datastorage.MedicineListAdapter;
import com.example.pillreminderapp.datastorage.MedicineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class MedInfoActivity extends AppCompatActivity {
    private MedicineViewModel MedicineViewModel;
    public static final int NEW_Medicine_ACTIVITY_REQUEST_CODE = 1;
// lolololol
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_info);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MedicineListAdapter adapter = new MedicineListAdapter(new MedicineListAdapter.MedicineDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MedicineViewModel = new ViewModelProvider(this).get(MedicineViewModel.class);
        MedicineViewModel.getAllMedicines().observe(this, Medicines -> {
            // Update the cached copy of the Medicines in the adapter.
            adapter.submitList(Medicines);
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MedInfoActivity.this, NewMedicineActivity.class);
            startActivityForResult(intent, NEW_Medicine_ACTIVITY_REQUEST_CODE);
        });


    }

    public void goToMainActivity (View view){

        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_Medicine_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Medicine Medicine = new Medicine( 0, data.getStringExtra(NewMedicineActivity.NAME), data.getStringExtra(NewMedicineActivity.TYPE), data.getStringExtra(NewMedicineActivity.TIME));
            MedicineViewModel.insert(Medicine);
            //NewMedicineActivity.EXTRA_REPLY, 0), data.getStringExtra(NewMedicineActivity.EXTRA_REPLY), NewMedicineActivity.EXTRA_REPLY, NewMedicineActivity.EXTRA_REPLY
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();

            System.out.println(data.getStringExtra(NewMedicineActivity.NAME));
            System.out.println(data.getStringExtra(NewMedicineActivity.TIME));
            System.out.println(data.getStringExtra(NewMedicineActivity.TYPE));
        }


    }

}