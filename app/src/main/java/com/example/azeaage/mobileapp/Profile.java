package com.example.azeaage.mobileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objects.SalesOrders;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<SalesOrders> SalesOrdersList;
    SalesOrders salesOrders[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ListView listView = (ListView)findViewById(R.id.orders_list);
salesOrders=new SalesOrders[5];

        String string = "January 2, 2010";
        Date date = null;
        //int orderId, Date orderDate, String shippingAddress, System shippingCity, LatLng locationCoordinate,
       // double orderTotal, int SAPInvoiceNo, String completionCode, boolean isCancelled, String createdBy
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date parsed =  format.parse("20110210");
            java.sql.Date sql = new java.sql.Date(parsed.getTime());//2011-02-10 out put


            LatLng latLng = new LatLng(24.766950, 46.775897);

            salesOrders[1] = new SalesOrders(1, sql, "Riydh King abdullah","riyadh" ,latLng , 1000.0,300000008, "", false, "customer");
            salesOrders[2] = new SalesOrders(1, sql, "Riydh King abdullah","riyadh" ,latLng , 1000.0,300567567, "", false, "customer");

        }catch (ParseException e) {
            e.printStackTrace();
        }

      /*  try {
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
          //   date = (Date) format.parse(string);

        } catch (ParseException e) {
            e.printStackTrace();
        }*/


            SalesOrdersList=new ArrayList<SalesOrders>();
            SalesOrdersList.add(salesOrders[1]);
            SalesOrdersList.add(salesOrders[2]);

      listView.setAdapter(new orderList(SalesOrdersList,getApplicationContext()));
        listView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
