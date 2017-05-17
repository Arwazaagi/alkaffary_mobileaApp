package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.azeaage.mobileapp.adapters.orderList;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import objects.Products;
import objects.SalesOrderDetails;
import objects.SalesOrders;

public class Profile extends AppCompatActivity {
    private ArrayList<SalesOrders> SalesOrdersList;
    public static SalesOrders salesOrders;
    ArrayList<SalesOrderDetails>salesOrderDetails;
    SalesOrderDetails salesOrderDetail[];
    java.sql.Date sql;
    LatLng latLng=null;
    Products products[];
    userSessionManeger session;
    String customerPhone,DocNum,Latitude,Longitude,address,DocDate,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // onButtonPressed("Wholesale");
        setContentView(R.layout.activity_profile);
        ListView listView = (ListView)findViewById(R.id.orders_list);

        String string = "January 2, 2010";
        Date date = null;
        session =new userSessionManeger(getBaseContext());
        SalesOrdersList=new ArrayList<SalesOrders>();
        if(session.checkLogin())
            finish();
         background b= new background(getBaseContext());
        try {
            HashMap<String, String> user = session.getUserDetails();
            customerPhone = user.get(userSessionManeger.KEY_PHONE);

                System.out.println(customerPhone + " customer fon");
                String result = b.execute("GetInvoicesByPhone", "0552959085").get();
                result = result.substring(result.indexOf('=') + 1, result.indexOf(';'));
                JSONArray jsonarray = new JSONArray(result);
                System.out.println("Length of json "+jsonarray.length());
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    DocNum = jsonobject.getString("DocNum");
                    Latitude = jsonobject.getString("Latitude");
                    Longitude = jsonobject.getString("Longitude");
                    address=jsonobject.getString("U_Address");
                    DocDate=jsonobject.getString("DocDate");
                    DocDate=DocDate.substring(0,4)+"/"+DocDate.substring(4,6)+"/"+DocDate.substring(6,8);//convert the date in readable format
                    total=jsonobject.getString("DocTotal");

                    salesOrders = new SalesOrders(DocNum, Latitude, Longitude, address,DocDate,total);
                    SalesOrdersList.add(salesOrders);


                }
            } catch (JSONException | InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        System.out.println("Length of invoices "+SalesOrdersList.size());

        //ArrayList<SalesOrderDetails> salesOrderDetails;
        listView.setAdapter(new orderList(SalesOrdersList,getApplicationContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent productIntent = new Intent(getApplication().getApplicationContext(),Order_Details_Activity.class);
                //To pass:
                productIntent.putExtra("salesOrder",SalesOrdersList.get(position));
                startActivity(productIntent);
            }


        });
    }




}
