package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import objects.Products;
import objects.SalesOrderDetails;
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
        ListView product_list = (ListView)findViewById(R.id.products_listview);

        int position=i.getIntExtra("salesOrder",0);


        invoiceNum=(TextView)findViewById(R.id.invoice_num_tv);
        creationDate=(TextView)findViewById(R.id.creation_date_tv);
        salesOrders= Profile.salesOrders[position+1];
        TextView[]textViews=new TextView[5];
        invoiceNum.setText(salesOrders.getSAPInvoiceNo()+"");
        creationDate.setText(salesOrders.getOrderDate().toString());
        ArrayList<SalesOrderDetails> salesOrderDetails=salesOrders.getSalesOrderDetails();
     /*   TableLayout tableLayout = (TableLayout)findViewById(R.id.product_table);
        for (int a = 0; a <salesOrderDetails.size(); a++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setGravity(Gravity.CENTER);

            for (int j = 0; j < 5; j++)
            {
                textViews[j]=new TextView(this);
                textViews[j].setGravity(Gravity.CENTER);
               // textViews[j].setText(salesOrderDetails.get(a).getRecordID()+"");
                tableRow.addView(textViews[j]);

            }
            textViews[0].setText(salesOrderDetails.get(a).getRecordID()+"");
            textViews[1].setText(salesOrderDetails.get(a).getQty()+"");

            textViews[2].setText(salesOrderDetails.get(a).getPrice()+"RS");
            textViews[3].setText("20%");
            textViews[4].setText(salesOrderDetails.get(a).getLineTotal()+"RS");
            tableLayout.addView(tableRow);
        }
       // setContentView(tableLayout);*/
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
