package com.example.pillreminderapp.activities;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
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


    @RequiresApi(api = Build.VERSION_CODES.O)
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
                String Time = getTime();
                String Type = getType();
                replyIntent.putExtra(NAME, Medicine);
                replyIntent.putExtra(TIME, Time);
                replyIntent.putExtra(TYPE, Type);
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

//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(this);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medicine_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);


        TimePicker picker = (TimePicker) findViewById(R.id.timePicker1);
        picker.setIs24HourView(false);

//        tvw=(TextView)findViewById(R.id.textView2);
//        eText=(EditText) findViewById(R.id.editText1);
//        eText.setInputType(InputType.TYPE_NULL);
//        eText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar cldr = Calendar.getInstance();
//                int day = cldr.get(Calendar.DAY_OF_MONTH);
//                int month = cldr.get(Calendar.MONTH);
//                int year = cldr.get(Calendar.YEAR);
//
//                pickerDate = new DatePickerDialog(NewMedicineActivity.this,
//                        new DatePickerDialog.OnDateSetListener()
//                        {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
//                            {
//                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                            }
//                        }, year, month, day);
//                pickerDate.show();
//            }
//        });
//        btnGet=(Button)findViewById(R.id.button1);
//        btnGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvw.setText("Selected Date: "+ eText.getText());
//            }
//        });
    }

    private String getType() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedID = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedID);
        return (String) radioButton.getText();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTime (){
        TimePicker picker = (TimePicker) findViewById(R.id.timePicker1);
//        LocalTime time = LocalTime.of(, picker.getMinute());

        String currentTime;

        return currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    }

    public boolean checkTime (String timeNow, String timeMed){

        if (timeNow.equals(timeMed)) {

            return true;

        } else {

            return false;

        }

    }

    //Method helps save selected item in drop down menu
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addNotification(String Medicine, String Time, String Type) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 25);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationmessage = new Intent(getApplicationContext(), NotificationMessage.class);

//This is alarm manager
        PendingIntent pi = PendingIntent.getService(this, 0 , notificationmessage, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
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
