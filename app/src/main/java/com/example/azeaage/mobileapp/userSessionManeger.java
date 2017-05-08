package com.example.azeaage.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by AZeaage on 4/17/2017.
 */

public class userSessionManeger {
    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    Context context;
    int PRIVATE_MODE =0;
    private static final String PREFER_NAME="AndroidExamplePref";
    private static final String IS_USER_LOGIN="IsUserLoggedIn";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PHONE="phone";
    public static final String KEY_ID="id";

    //constructor

    public userSessionManeger(Context context) {
        this.context = context;
        preferences=context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor=preferences.edit();

    }

    public void createUserLoginSession(String name,String email,String id, String phone1){
        editor.putBoolean(IS_USER_LOGIN,true);
        //shared the name in pref
        editor.putString(KEY_NAME,name);
        //shared the email in pref
        editor.putString(KEY_EMAIL,email);
        //shared the email in pref
        editor.putString(KEY_PHONE,phone1);
        //shared the email in pref
        editor.putString(KEY_ID,id);

        editor.commit();
    }

    public boolean checkLogin(){

        if(!this.IsUserLoggedIn()){

            Intent i = new Intent(context,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(i);
            return true;

        }
        return false;
    }

    private boolean IsUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }

    public HashMap<String,String>getUserDetails(){

        HashMap<String,String> user=new HashMap<>();
        user.put(KEY_NAME,preferences.getString(KEY_NAME,null));
        user.put(KEY_EMAIL,preferences.getString(KEY_EMAIL,null));
        user.put(KEY_PHONE,preferences.getString(KEY_PHONE,null));
        user.put(KEY_ID,preferences.getString(KEY_ID,null));
        return user;
    }

    public void logoutUser(){

        editor.clear();
        editor.commit();

        Intent i = new Intent(context,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
    }
}
