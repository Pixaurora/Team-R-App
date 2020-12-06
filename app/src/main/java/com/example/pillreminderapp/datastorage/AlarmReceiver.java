package com.example.pillreminderapp.datastorage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pillreminderapp.R;
import com.example.pillreminderapp.activities.NewMedicineActivity;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class AlarmReceiver /*extends BroadcastReceiver*/
{
/*
    int NotificationID = 0;

    @Override
    public void onReceive(Context context, Intent intent)
    {

    }

    public void addNotification(String Medicine, String Time, String Type)
    {
        Intent intent = new Intent(this, NewMedicineActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        long notifTime = Long.parseLong(Time);

        alarmManager.set(AlarmManager.RTC_WAKEUP, notifTime, pendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel1")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Time to take your Medicine!")
                .setContentText("Time to take your " + Medicine + " " + Type)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
        NotificationId++;
    }

*/
}