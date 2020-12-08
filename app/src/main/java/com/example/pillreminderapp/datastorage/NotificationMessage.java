package com.example.pillreminderapp.datastorage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pillreminderapp.R;

public class NotificationMessage extends BroadcastReceiver
{


    @Override
    public void onReceive(Context context, Intent intent)
    {
        showNotification(context);
    }

    public void showNotification(Context context)
    {
        /*PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, NotificationMessage.class), 0);

        long timeAtButtonClick = System.currentTimeMillis();
        long notifTime = //Long.parseLong(Time);



                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel1")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Time to take your Medicine!")
            .setContentText("Time to take your " + Medicine + " " + Type)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
        NotificationId++;
        */

    }

}
