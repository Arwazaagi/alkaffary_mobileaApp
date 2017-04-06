package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import objects.Products;
import objects.SalesOrders;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Order_Details_Activity extends AppCompatActivity {
    private int mData;
    TextView branchCode,phoneNum,invoiceNum,creationDate;
     SalesOrders salesOrders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detalis);
        // To retrieve object in second Activity
        Intent i = getIntent();
        ListView product_list = (ListView)findViewById(R.id.product_list);
        int position=i.getIntExtra("salesOrder",0);

        branchCode=(TextView)findViewById(R.id.Branch_Code_tv);
        phoneNum=(TextView)findViewById(R.id.phone_num_tv);
        invoiceNum=(TextView)findViewById(R.id.invoice_num_tv);
        creationDate=(TextView)findViewById(R.id.creation_date_tv);
        salesOrders= Profile.salesOrders[position+1];
        branchCode.setText(salesOrders.getOrderId()+"");
        invoiceNum.setText(salesOrders.getSAPInvoiceNo()+"");
        creationDate.setText(salesOrders.getOrderDate().toString());
        product_list.setAdapter(new productList(salesOrders.getSalesOrderDetails(),getApplicationContext()));

        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent product_intent=new Intent(getApplication().getApplicationContext(),product_activity.class);
               // Customer customer=new Customer("arwa","Alzeaagi","arwazeaagi@gmail.com","0556693340","555885");
                Products products =salesOrders.getSalesOrderDetails().get(position+1).getProducts();
                product_intent.putExtra("product",products);
                startActivity(product_intent);
            }
        });

    }


}
