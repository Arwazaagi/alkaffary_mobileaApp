package com.example.azeaage.mobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by AZeaage on 4/5/2017.
 */

public class background extends AsyncTask <String ,Void ,String> {
    Context context;
    private AppCompatActivity appCompatActivity;
    private ProgressDialog dialog ;
    private final String tokenKey="0DE4EA23-6420-43C2-B853-18E8D6B32837";

    public background(Context context) {
        this.context = context;
        appCompatActivity=(AppCompatActivity)new AppCompatActivity();
        dialog= new ProgressDialog(context);
    }

    @Override
    protected String doInBackground(String... voids) {
        String method = voids[0];
        String result="";
        CallSoap callSoap =new CallSoap();
        if(method.equals("register")){
            String registerInfo[]=new String[8];
            for(int i=0;i<registerInfo.length;i++)
            registerInfo[i]=voids[i+1];
            result=callSoap.RegisterUser(tokenKey ,registerInfo[0],registerInfo[1],registerInfo[2],registerInfo[3],
                    registerInfo[4],registerInfo[5],registerInfo[6],registerInfo[7]);
        }
        //callSoap.GetCompanyInfo("0DE4EA23-6420-43C2-B853-18E8D6B32837");
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
        System.out.println("Out put **********"+s);

    }
}
