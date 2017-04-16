package com.example.azeaage.mobileapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import objects.Customer;

import static android.Manifest.permission.READ_CONTACTS;

public class Register_Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
    // UI references.
    private AutoCompleteTextView first_name_tv;
    private AutoCompleteTextView last_name_tv;
    private AutoCompleteTextView email_tv;
    private EditText password_et;
    private AutoCompleteTextView phone_tv;
    private Button submit;
    private BroadcastReceiver broadcastReceiver;
    String location ="";
    public static Customer new_Customer;
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    String first_Name,last_Name,Email,Phone_number,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        first_name_tv=(AutoCompleteTextView)findViewById(R.id.first_name);
        last_name_tv=(AutoCompleteTextView)findViewById(R.id.last_name);
        email_tv=(AutoCompleteTextView)findViewById(R.id.email);
        password_et =(EditText)findViewById(R.id.password);
        phone_tv=(AutoCompleteTextView)findViewById(R.id.phone_number1);
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

             //   new_Customer=new Customer(first_Name,Email,Phone_number,Password);
                Toast.makeText(getApplication(),new_Customer.toString(),Toast.LENGTH_LONG).show();
                if(!runtime_permissions()){
                    //We dont need permissions
                    MapsActivity mapsActivity = new MapsActivity();
                    mapsActivity.setCustomer(new_Customer);
                    Intent maps_intent = new Intent(getApplication(), MapsActivity.class);
                    // set Fragmentclass Arguments
                    startActivity(maps_intent);
                    finish();
                }
            else{
                    Intent main_intent=new Intent(getApplication(),MainActivity.class);
                    startActivity(main_intent);
                }}
        });

    }
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(email_tv, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
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

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
