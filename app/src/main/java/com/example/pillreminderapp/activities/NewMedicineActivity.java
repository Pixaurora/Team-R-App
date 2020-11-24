package com.example.pillreminderapp.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewMedicineActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    String IMEINumber="",token="";
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
                gettokenagainstimei(IMEINumber);
            }
            finish();
        });
        getDeviceIMEI();
    }
    @SuppressLint("HardwareIds")
    public void getDeviceIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(NewMedicineActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NewMedicineActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
        IMEINumber = telephonyManager.getDeviceId();
    }
    public void gettokenagainstimei(final String imei) {
        StringRequest request = new StringRequest(Request.Method.POST, "http://www.studentfyp.online/getimeitoken.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("res",response);
                    JSONObject object = new JSONObject(response);
                    JSONArray complaints = object.getJSONArray("result");
                    for (int i = 0; i < complaints.length(); i++) {
                        Log.d("complaints", complaints + "");
                        JSONObject object1 = complaints.getJSONObject(i);
                        String token = object1.getString("token");
                        Log.d("tokenthis",token);
                        sendnotification(token);
                        finishAndRemoveTask();
                    }
                } catch (JSONException e) {

                    //  CustomProgressDialog.hide();

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // CustomProgressDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                return super.getParams();
                Map<String, String> params = new HashMap<String, String>();
                params.put("imei", "353288080775442" + "");
                return params;
            }
        };
        RequestQueue rQueue = Volley.newRequestQueue(NewMedicineActivity.this);
        rQueue.add(request);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void sendnotification(final String token)
    {
        StringRequest request = new StringRequest(Request.Method.POST,"http://www.studentfyp.online/empiremessage.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("respone",response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(Quiz.this, "Error: ", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(NewMedicineActivity.this).setMessage("An error Occured! Please try again!").setTitle("Alert!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                Log.d("verror","Error: "+volleyError.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("token",""+token);
                return parameters;
            }
        };
        RequestQueue rQueue = Volley.newRequestQueue(NewMedicineActivity.this);
        rQueue.add(request);
    }
    public String getTimeNow() {

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
