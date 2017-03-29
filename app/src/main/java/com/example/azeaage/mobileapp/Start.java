package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Start extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.activity_start);
        Button login_Button = (Button)findViewById(R.id.button_login);
        Button register_Button = (Button)findViewById(R.id.button_register);
        Button guest_Button = (Button)findViewById(R.id.button_guest);
        login_Button.setOnClickListener(this);
        guest_Button.setOnClickListener(this);
        register_Button.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.button_login :
                Intent login_intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(login_intent);
                break;
            case R.id.button_guest:
                Intent main_intent = new Intent(getApplication(), MainActivity.class);
                startActivity(main_intent);
                break;
            case R.id.button_register:
                Intent register_intent = new Intent(getApplication(), Register_Activity.class);
                startActivity(register_intent);
                break;

        }
    }
}