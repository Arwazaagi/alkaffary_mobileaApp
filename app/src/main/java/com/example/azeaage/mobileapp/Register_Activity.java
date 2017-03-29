package com.example.azeaage.mobileapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class Register_Activity extends AppCompatActivity {
    // UI references.
    private AutoCompleteTextView first_name_tv;
    private AutoCompleteTextView last_name_tv;
    private AutoCompleteTextView email_tv;
    private EditText password_et;
    private AutoCompleteTextView phone_tv;
    private Button submit;
    private BroadcastReceiver broadcastReceiver;
    String location ="";
    public static User new_user;

    String first_Name,last_Name,Email,Phone_number,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        first_name_tv=(AutoCompleteTextView)findViewById(R.id.first_name);
        last_name_tv=(AutoCompleteTextView)findViewById(R.id.last_name);
        email_tv=(AutoCompleteTextView)findViewById(R.id.email);
        password_et =(EditText)findViewById(R.id.password);
        phone_tv=(AutoCompleteTextView)findViewById(R.id.phone_number);
        submit =(Button)findViewById(R.id.register_button);
        runtime_permissions();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first_Name=first_name_tv.getText().toString();
                last_Name=last_name_tv.getText().toString();
                Email=email_tv.getText().toString();
                Phone_number=phone_tv.getText().toString();
                Password=password_et.getText().toString();
                new_user=new User(first_Name,last_Name,Email,Phone_number,Password);
                System.out.println(new_user+"^^^^^^^^^^^^^^^^^^^^^");
                if(!runtime_permissions()){
                    //We dont need permissions
                    MapsActivity mapsActivity = new MapsActivity();
                    mapsActivity.setUser(new_user);
                    Intent maps_intent = new Intent(getApplication(), MapsActivity.class);

                    // set Fragmentclass Arguments


                    startActivity(maps_intent);
                }            }
        });

    }
    private void takeLocation() {
        Intent i = new Intent(this,GPS_Service.class);
    //    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

// Launch the new activity and add the additional flags to the intent

       startService(i);


    }


    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode ==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                takeLocation();
            }
            else{
                runtime_permissions();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    location=intent.getExtras().get("coordinates").toString();
                    System.out.print("outtttttttttttt "+location);

                }

            };}
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }
}
