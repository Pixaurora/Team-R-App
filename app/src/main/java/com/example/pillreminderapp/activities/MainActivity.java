package com.example.pillreminderapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.example.pillreminderapp.R;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    Button daily_reminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daily_reminder=findViewById(R.id.daily_reminder);
        checkBuildVersion();
        buttonClicked();
    }

    private void checkBuildVersion() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) { /// only newer versions of android have channels
            NotificationChannel channel1 = new NotificationChannel("Channel1", "Channel 1", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

    private void buttonClicked() {
        daily_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Title";
                String notif = "Take your pills!";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Channel1");
                builder.setSmallIcon(R.drawable.pill_icon);
                builder.setContentTitle(title);  ///set characteristics of notification
                builder.setContentText(notif);
                builder.setAutoCancel(true);
                Intent intent= new Intent(MainActivity.this, NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("reminder",notif);
                PendingIntent pendingIntent =PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager managerCompat = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                managerCompat.notify(1, builder.build());

            }
        }
        );
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
