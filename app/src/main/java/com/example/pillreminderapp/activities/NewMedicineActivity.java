package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pillreminderapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewMedicineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

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
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medicine_array, android.R.layout.simple_spinner_item);
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

    //Method helps save selected item in drop down menu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l)
    {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}


