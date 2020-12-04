package com.example.pillreminderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pillreminderapp.R;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class NewMedicineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int REQUEST_CODE = 101;
    String IMEINumber="",token="";
    public static final String NAME = "com.example.android.Medicinelistsql.REPLY1";
    public static final String TYPE = "com.example.android.Medicinelistsql.REPLY2";
    public static final String TIME = "com.example.android.Medicinelistsql.REPLY3";
    private EditText mEditMedicineView;
    DatePickerDialog pickerDate;
    EditText eText;
    Button btnGet;
    TextView tvw;
    int NotificationId = 0;


    public static final String EXTRA_REPLY = "com.example.android.Medicinelistsql.REPLY";

    private EditText mEditMedicineView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);
        mEditMedicineView = findViewById(R.id.edit_Medicine);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditMedicineView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String Medicine = mEditMedicineView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, Medicine);
                setResult(RESULT_OK, replyIntent);
                try {
                    addNotification(Medicine, Time, Type);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                gettokenagainstimei(IMEINumber);

            }
            finish();
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medicine_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public String getTimeNow (){

        String hour = String.format("%02d", picker.getHour());
        String minute = String.format("%02d", picker.getMinute());


        return hour + ":" + minute;
    }

    //    return currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

  //  }

    public boolean checkTime (String timeNow, String timeMed){

        if (timeNow.equals(timeMed)){

            return true;

        } else {

            return false;

        }

    }

    //Method helps save selected item in drop down menu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l)
    {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addNotification(String Medicine, String Time, String Type) throws ParseException {
        Intent intent = new Intent(this, NewMedicineActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        long notifTime = dateFormat.parse(Time).getTime();

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
}


