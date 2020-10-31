package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.example.pillreminderapp.R;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager =NotificationManagerCompat.from(this);
    }
    public void sendOnChannel1(View v){
        String title = "Title";
        String notif= "Take your pills!";
        Notification notification = new NotificationCompat.Builder(this, AppNotif.Channel_1_ID).setSmallIcon(R.drawable.pill_icon)
                .setContentTitle(title)  ///set characteristics of notification
                .setContentText(notif)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .build();
        notificationManager.notify(1,notification);
    }

        public void goToSettingsActivity (View view){

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        }

        public void goToCalendarActivity (View view){

            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);

        }

        public void goToMedInfoActivity (View view){

            Intent intent = new Intent(this, MedInfoActivity.class);
            startActivity(intent);

        }

        public void goToMedsActivity (View view){

            Intent intent = new Intent(this, MedsActivity.class);
            startActivity(intent);

        }

}
