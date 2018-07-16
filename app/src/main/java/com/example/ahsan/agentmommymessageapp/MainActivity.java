package com.example.ahsan.agentmommymessageapp;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    EditText number;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("childlost");
        requestSmsPermission();
        FirebaseApp.initializeApp(this);
        final Preference preference = new Preference(MainActivity.this);

        number  = findViewById(R.id.number);
        btn = findViewById(R.id.btn);

        number.setText(preference.getNumber());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number.getText().toString().equals("") || number.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter number", Toast.LENGTH_SHORT).show();
                } else if(!isValidPhoneNo(number.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                } else {
                    preference.setNumber(number.getText().toString());


                    Toast.makeText(MainActivity.this, "Number saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Start the background Firebase activity
      //  startService(new Intent(this, FirebaseBackgroundService.class));
    }
    public static boolean isValidPhoneNo(CharSequence iPhoneNo) {
        return !TextUtils.isEmpty(iPhoneNo) &&
                Patterns.PHONE.matcher(iPhoneNo).matches();
    }
    private static final int PERMISSION_SEND_SMS = 123;

    private void requestSmsPermission() {

        // check permission is given
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                ) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.SEND_SMS},
                    PERMISSION_SEND_SMS);
        } else {
            // permission already granted run sms send
     //       sendSms(phone, message);
        }

        // check permission is given
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                ) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_SMS},
                    101);
        } else {
            // permission already granted run sms send
            //       sendSms(phone, message);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_SEND_SMS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
   //                 sendSms(phone, message);
                } else {
                    // permission denied
                }
                break;
            }
            case 101 :{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    //                 sendSms(phone, message);
                } else {
                    // permission denied
                }

                break;
            }
        }
    }


}
