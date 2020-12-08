package com.example.pillreminderapp.datastorage;

/* import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pillreminderapp.R;
import com.example.pillreminderapp.activities.NewMedicineActivity;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService; */


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pillreminderapp.R;

import com.example.pillreminderapp.TimeStringConverter;
import com.example.pillreminderapp.activities.NewMedicineActivity;
import com.example.pillreminderapp.datastorage.NotificationMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.ALARM_SERVICE;

public class AlarmReceiver extends BroadcastReceiver
{

    int NotificationId = 0;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //showNotification(context);
    }

    public void showNotification(String Medicine, String Time, String Type)
    {
     /*   Intent intent = new Intent(this, NewMedicineActivity.class);
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

      */
    }


}