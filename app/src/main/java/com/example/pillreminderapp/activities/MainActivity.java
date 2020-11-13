package com.example.pillreminderapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Toast;

import com.example.pillreminderapp.R;
import com.example.pillreminderapp.datastorage.Medicine;
import com.example.pillreminderapp.datastorage.MedicineListAdapter;
import com.example.pillreminderapp.datastorage.MedicineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private MedicineViewModel mMedicineViewModel;
    public static final int NEW_Medicine_ACTIVITY_REQUEST_CODE = 1;
    Button daily_reminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MedicineListAdapter adapter = new MedicineListAdapter(new MedicineListAdapter.MedicineDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMedicineViewModel = new ViewModelProvider(this).get(MedicineViewModel.class);
        mMedicineViewModel.getAllMedicines().observe(this, Medicines -> {
            // Update the cached copy of the Medicines in the adapter.
            adapter.submitList(Medicines);
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewMedicineActivity.class);
            startActivityForResult(intent, NEW_Medicine_ACTIVITY_REQUEST_CODE);
        });
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/*
        if (requestCode == NEW_Medicine_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Medicine Medicine = new Medicine(data.getStringExtra(NewMedicineActivity.EXTRA_REPLY));
            mMedicineViewModel.insert(Medicine);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }

 */
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
