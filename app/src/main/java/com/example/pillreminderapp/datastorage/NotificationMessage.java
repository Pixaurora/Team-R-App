package com.example.pillreminderapp.datastorage;


import androidx.annotation.RequiresApi;
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
import com.example.pillreminderapp.datastorage.NotificationMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NotificationMessage extends BroadcastReceiver
{
    int NotificationID = 0;

    @Override
    public void onReceive(Context context, Intent intent)
    {

    }

    @SuppressWarnings("checkstyle:LineLength")
    public void showNotification(Context context)
    {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, NotificationMessage.class), 0);

        long timeAtButtonClick = System.currentTimeMillis();
        //long notifTime = Long.parseLong(Time);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel1")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Time to take your Medicine!")
            .setContentText("Time to take your ")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());
        NotificationID++;


    }

}
