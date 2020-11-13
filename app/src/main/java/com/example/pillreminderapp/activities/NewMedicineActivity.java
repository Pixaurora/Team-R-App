package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.pillreminderapp.R;

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
    }
}


