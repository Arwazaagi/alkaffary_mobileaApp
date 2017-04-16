package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Start extends AppCompatActivity implements View.OnClickListener {

    Typeface typeface1,typeface2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.activity_start);
        Button login_Button = (Button)findViewById(R.id.button_login);
        Button register_Button = (Button)findViewById(R.id.button_register);
        login_Button.setOnClickListener(this);
        register_Button.setOnClickListener(this);
        typeface1=Typeface.createFromAsset(getAssets(),"fonts/cerbeticabold.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"fonts/cerbetica_regular.ttf");
      login_Button.setTypeface(typeface1);
      register_Button.setTypeface(typeface2);
        /*  new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app Next activity
                openMenuAct();
            }
        }, 10000);*/

      ImageView logo=(ImageView)findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           openMenuAct();
            }
        });
    }

    private void openMenuAct(){
        Intent i = new Intent(Start.this, MainActivity.class);
        startActivity(i);
        // close this activity
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.button_login :
                Intent login_intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(login_intent);
                finish();

                break;
            case R.id.button_register:
                Intent register_intent = new Intent(getApplication(), registerActivity.class);
                startActivity(register_intent);
                finish();
                break;
        }

        }
}
