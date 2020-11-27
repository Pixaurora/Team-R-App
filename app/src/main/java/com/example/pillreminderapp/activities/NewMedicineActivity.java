package com.example.pillreminderapp.activities;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pillreminderapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewMedicineActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    String IMEINumber="",token="";
    public static final String EXTRA_REPLY = "com.example.android.Medicinelistsql.REPLY";
    private EditText mEditMedicineView;
    DatePickerDialog pickerDate;
    EditText eText;
    Button btnGet;
    TextView tvw;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
                gettokenagainstimei(IMEINumber);
            }
            finish();
        });

//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(this);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medicine_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);


        TimePicker picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(false);

        tvw=(TextView)findViewById(R.id.textView2);
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




    public String getTimeNow (){

        String currentTime;

        return currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    public boolean checkTime(String timeNow, String timeMed) {

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

}
