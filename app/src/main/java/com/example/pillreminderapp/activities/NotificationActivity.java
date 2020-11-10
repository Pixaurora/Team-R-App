package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pillreminderapp.R;


import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        TextView textView=findViewById(R.id.daily_reminder);
        String message = getIntent().getStringExtra("Reminder");
        textView.setText(message);
    }
}