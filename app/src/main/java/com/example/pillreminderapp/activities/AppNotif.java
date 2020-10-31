package com.example.pillreminderapp.activities;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class AppNotif extends Application {   ///this a class for the notifications
    public static final String Channel_1_ID="channel1";
    public static final String Channel_2_ID="channel2";

    @Override
    public void onCreate(){
        super.onCreate();
    }
    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){ /// only newer versions of android have channels
            NotificationChannel channel1 = new NotificationChannel(Channel_1_ID,"Channel 1", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is channel 1 for notifications");
            NotificationChannel channel2 = new NotificationChannel(Channel_2_ID,"Channel 1", NotificationManager.IMPORTANCE_HIGH);
            channel2.setDescription("This is channel 2 for notifications"); //two channels for different notifcations
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
