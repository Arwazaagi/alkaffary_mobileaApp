package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objects.Products;
import objects.SalesOrderDetails;
import objects.SalesOrders;

public class Profile extends AppCompatActivity {
    private ArrayList<SalesOrders> SalesOrdersList;
    public static SalesOrders salesOrders[];
    ArrayList<SalesOrderDetails>salesOrderDetails;
    SalesOrderDetails salesOrderDetail[];
    java.sql.Date sql;
    LatLng latLng=null;
    Products products[];

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
           sql = new java.sql.Date(parsed.getTime());//2011-02-10 out put


            latLng = new LatLng(24.766950, 46.775897);

            salesOrders[1] = new SalesOrders(1, sql, "Riydh King abdullah","riyadh" ,latLng , 1000.0,30999008, "", false, "customer");
            salesOrders[2] = new SalesOrders(1, sql, "Riydh King abdullah","riyadh" ,latLng , 1000.0,300567567, "", false, "customer");
            products =new Products[6];
            products[1]=new Products(112,"غرفة نوم", "BedRoom","furniture","furniture",10000,true);
            products[2]=new Products(132,"غرفة طعام", "Dining Room","furniture","furniture",20000,true);
            products[3]=new Products(142,"غرفة جلوس", "living room","furniture","furniture",22000,true);
            products[4]=new Products(152,"غرفة نوم", "BedRoom","furniture","furniture",10000,true);
            salesOrderDetail=new SalesOrderDetails[5];
            salesOrderDetail[1]=new SalesOrderDetails(1,"m",2,10000.0,2*10000,sql,products[1]);
            salesOrderDetail[2]=new SalesOrderDetails(2,"m",5,10000.0,5*10000,sql,products[2]);
            salesOrderDetail[3]=new SalesOrderDetails(3,"m",3,10000.0,3*10000,sql,products[3]);
            salesOrderDetail[4]=new SalesOrderDetails(4,"m",1,10000.0,1*10000,sql,products[4]);
            salesOrderDetails=new ArrayList<SalesOrderDetails>();
            for(int i=1;i<=4;i++)
            salesOrderDetails.add(salesOrderDetail[i]);
            salesOrders[1].setSalesOrderDetails(salesOrderDetails);
            salesOrders[2].setSalesOrderDetails(salesOrderDetails);


        }catch (ParseException e) {
            e.printStackTrace();
        }


            SalesOrdersList=new ArrayList<SalesOrders>();
            SalesOrdersList.add(salesOrders[1]);
            SalesOrdersList.add(salesOrders[2]);

      listView.setAdapter(new orderList(SalesOrdersList,getApplicationContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent productIntent = new Intent(getApplication().getApplicationContext(),Order_Details_Activity.class);
                //To pass:
                productIntent.putExtra("salesOrder",position);
                startActivity(productIntent);
            }
        });
    }


}
