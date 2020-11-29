package com.example.pillreminderapp.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Response;
import com.example.pillreminderapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    private String IMEINumber="dummy",token="dummy";
    private static final String TAG = "MainActivity";
    public static final String CHANNEL_ID = "channel1";
    private static String token1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBuildVersion();
        /*
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        token = task.getResult();
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                        registerimeinumber(IMEINumber,token);
                        token1=token;
                    }
                });
        getDeviceIMEI();
        */


    }
    private void checkBuildVersion() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) { /// only newer versions of android have channels
           NotificationChannel channel1 = new NotificationChannel("channel1", "Channel 1", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);



        }
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

            Intent intent = new Intent(this, NewMedicineActivity.class);
            startActivity(intent);

        }

    @SuppressLint("HardwareIds")
    public void getDeviceIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
        IMEINumber = telephonyManager.getDeviceId();
        registerimeinumber(IMEINumber,token);
    }
    @Override
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
    public void registerimeinumber(final String imei,final String token)
    {
        StringRequest request = new StringRequest(Request.Method.POST,"http://www.studentfyp.online/insertimei.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject data = new JSONObject(response);
                    int success=data.getInt("success");
                    String msg=data.getString("msg");
                    if(success==1)
                    {
                    }
                    else
                    {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(Quiz.this, "Error: ", Toast.LENGTH_SHORT).show();
                Log.d("verror","Error: "+volleyError.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("imei",imei);
                parameters.put("token",token1);
                return parameters;
            }
        };
        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
    }
}
