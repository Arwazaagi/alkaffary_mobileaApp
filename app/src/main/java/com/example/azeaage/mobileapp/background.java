package com.example.azeaage.mobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

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
        System.out.println("chenk the method value :: "+method);
        switch (method) {
            case "register":

                result = callSoap.RegisterUser(tokenKey, voids[1], voids[2], voids[3], voids[4],
                        voids[5], voids[6], voids[7], voids[8]);
                break;
            case "login":
                String email = voids[1];
                String password = voids[2];
                result = callSoap.AuthenticateCustomer(tokenKey, email, password);

                break;
            case "companyInfo":
                result=callSoap.GetCompanyInfo(tokenKey);
                break;
            case "GetProductsList":
                result= callSoap.GetProductsList(tokenKey,voids[1], voids[2], voids[3], voids[4]);
                break;
            case "GetBranchesList":
                result= callSoap.GetBranchesList(tokenKey);
                break;
            case "GetInvoicesByPhone":
                result= callSoap.GetInvoicesByPhone(tokenKey,voids[1]);
                break;
            case "GetInvoiceByDocNum":
                result= callSoap.GetInvoiceByDocNum(tokenKey,voids[1]);
                break;
            case "SaveGPSLocation":
                result=callSoap.SaveGPSLocation(voids[1],voids[2],voids[3]);
                break;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        //Toast.makeText(context,s,Toast.LENGTH_LONG).show();
        System.out.println("Out put **********"+s);

    }
}
