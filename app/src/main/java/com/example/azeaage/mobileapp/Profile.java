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
    String customerPhone,DocNum,Latitude,Longitude,address;

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
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    DocNum = jsonobject.getString("DocNum");
                    Latitude = jsonobject.getString("Latitude");
                    Longitude = jsonobject.getString("Longitude");
                    background b1 = new background(getBaseContext());
                    String res = null;

                    res = b1.execute("GetInvoiceByDocNum", DocNum).get();

                    if (!res.contains("Error")) {
                        res = res.substring(res.indexOf('=') + 1, res.indexOf(';'));


                        JSONObject jsonobject1 = new JSONObject(res);
                        DocNum = jsonobject1.getString("DocNum");
                        Latitude = jsonobject1.getString("Latitude");
                        Longitude = jsonobject1.getString("Longitude");
                        address = jsonobject1.getString("U_Address");
                        salesOrders = new SalesOrders(DocNum, Latitude, Longitude, address);
                        SalesOrdersList.add(salesOrders);
                    }
                }
            } catch (JSONException | InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }


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
