package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pillreminderapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewMedicineActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.Medicinelistsql.REPLY";

    private EditText mEditMedicineView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);
        mEditMedicineView = findViewById(R.id.edit_Medicine);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditMedicineView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String Medicine = mEditMedicineView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, Medicine);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.medicine_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public String getTimeNow (){

        String currentTime;

        return currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    }

    public boolean checkTime (String timeNow, String timeMed){

        if (timeNow.equals(timeMed)){

            return true;

        } else {

            return false;

        }

    }
}


