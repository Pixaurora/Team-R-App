package com.example.pillreminderapp.activities;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pillreminderapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

@SuppressLint({"MissingFirebaseInstanceTokenRefresh", "Registered"})
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    Bitmap bitmap;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Uri sound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/");
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("New Message")
                .setContentText("Medicine Added")
                .setSmallIcon(R.drawable.app_icon)
                .setSound(sound)
                .build();

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(123, notification);
    }
    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }
}