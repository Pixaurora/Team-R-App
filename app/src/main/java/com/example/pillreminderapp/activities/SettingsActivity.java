package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.pillreminderapp.R;
import com.example.pillreminderapp.activities.MainActivity;

public class SettingsActivity extends AppCompatActivity {

    Button daily_reminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);


        daily_reminder = findViewById(R.id.daily_reminder);
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
                      String title = "Medicine Reminder";
                      String notif = "Take your medicine!";
                      NotificationCompat.Builder builder = new NotificationCompat.Builder(SettingsActivity.this, "Channel1");
                      builder.setSmallIcon(R.drawable.pill_icon);
                      builder.setContentTitle(title);  ///set characteristics of notification
                      builder.setContentText(notif);
                      builder.setAutoCancel(true);
                      Intent intent= new Intent(SettingsActivity.this, NotificationActivity.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      intent.putExtra("reminder",notif);
                      PendingIntent pendingIntent =PendingIntent.getActivity(SettingsActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                      NotificationManager managerCompat = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                      managerCompat.notify(1, builder.build());

                  }
              }
        );
    }

    public void goToMainActivity (View view){

        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

    }

}
