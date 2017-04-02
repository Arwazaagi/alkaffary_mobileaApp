package com.example.azeaage.mobileapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Order_Details_Activity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detalis);
        ListView product_list = (ListView)findViewById(R.id.product_list);
       // product_list.setAdapter(new orderList(SalesOrdersList,getApplicationContext()));

    }
}
