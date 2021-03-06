package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class menuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
                finish();
               break;
            case R.id.button_guest:
                Intent main_intent = new Intent(getApplication(), MainActivity.class);
                startActivity(main_intent);
                finish();
                break;
            case R.id.button_register:
                Intent register_intent = new Intent(getApplication(), registerActivity.class);
                startActivity(register_intent);
                finish();
                break;

    }}
}
