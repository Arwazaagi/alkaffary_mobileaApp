package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.azeaage.mobileapp.adapters.productList;
import com.example.azeaage.mobileapp.adapters.salesProductsAdapter;
import com.example.azeaage.mobileapp.fragments.MapsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import objects.Products;
import objects.SalesOrderDetails;
import objects.SalesOrders;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Order_Details_Activity extends AppCompatActivity {

    TextView invoiceNum,creationDate,locationTV,invoiceNumTV,totalTV;
     SalesOrders salesOrders;
    String DocNum,proName,UOM;
    Double proPriceBD,proPriceAD, proQty;
    ArrayList<Products>productsArrayList;
    Products product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detalis);
        // To retrieve object in second Activity
        Intent i = getIntent();
        ListView product_list = (ListView)findViewById(R.id.products_listview);

       // int position=i.getIntExtra("salesOrder",0);
        salesOrders=(SalesOrders) getIntent().getSerializableExtra("salesOrder");
        DocNum=salesOrders.getSAPInvoiceNo();
              System.out.println("Incoive num in order deyiales "+DocNum);

        invoiceNum=(TextView)findViewById(R.id.invoice_num_tv);
        creationDate=(TextView)findViewById(R.id.creation_date_tv);
        TextView price_TV = (TextView) findViewById(R.id.price_tv);
        locationTV=(TextView)findViewById(R.id.locationTV);
        TextView invoice_tv=(TextView)findViewById(R.id.invoice_tv);
        invoiceNumTV=(TextView)findViewById(R.id.invoice_num);
        totalTV=(TextView)findViewById(R.id.totalPrice);

        font f =new font();
        f.ChangeFontToBold(invoiceNum,this);
        f.ChangeFontToBold(creationDate,this);
        f.ChangeFontToLight(price_TV,this);
        f.ChangeFontToLight(locationTV,this);
        f.ChangeFontToBold(invoice_tv,this);
        f.ChangeFontToBold(invoiceNumTV,this);
        f.ChangeFontToBold(totalTV,this);

       // Button locationB=(Button)findViewById(R.id.setLocation);
        invoiceNum.setText(salesOrders.getSAPInvoiceNo());
        creationDate.setText(salesOrders.getOrderDate());
        price_TV.setText(salesOrders.getOrderTotal()+"");
        locationTV.setText(salesOrders.getShippingAddress());
        locationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent=new Intent(getApplicationContext(),MapsActivity.class);
                mapIntent.putExtra("docNum",DocNum);
                mapIntent.putExtra("setInvoiceLocation",'I');

                startActivity(mapIntent);
            }
        });
        productsArrayList=new ArrayList<>();
        background b=new background(getBaseContext());
        try {
            String result=b.execute("GetInvoiceDetailsByDocNum",DocNum).get();
            result = result.substring(result.indexOf('=') + 1, result.indexOf(';'));


            JSONArray jsonarray = new JSONArray(result);

            System.out.println("Length of json "+jsonarray.length());
            for (int a = 0; a < jsonarray.length();a++) {
                JSONObject jsonobject = jsonarray.getJSONObject(a);
                proName=jsonobject.getString("ItemName");
                proPriceBD=Double.parseDouble(jsonobject.getString("PriceBeforeDisc"));
                proPriceAD=Double.parseDouble(jsonobject.getString("PriceAfterDisc"));
                UOM=jsonobject.getString("UOM");
                proQty=Double.parseDouble(jsonobject.getString("Quantity"));
                String totalLine=jsonobject.getString("LineTotal");
                product=new Products(proName,proPriceBD,proPriceAD,UOM,proQty,totalLine);
                productsArrayList.add(product);
            }

            product_list.setAdapter(new salesProductsAdapter(productsArrayList,getApplicationContext()));
            product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent product_intent=new Intent(getApplication().getApplicationContext(),product_activity.class);
                    // Customer customer=new Customer("arwa","Alzeaagi","arwazeaagi@gmail.com","0556693340","555885");

                 //   product_intent.putExtra("product",productsArrayList.get(position+1));
                    startActivity(product_intent);
                }
            });
        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
       /* locationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent=new Intent(getApplicationContext(),MapsActivity.class);
                mapIntent.putExtra("docNum",DocNum);
                mapIntent.putExtra("setInvoiceLocation",'I');

                startActivity(mapIntent);
            }
        });*/
       /* salesOrders= Profile.salesOrders[position+1];
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
     /* product_list.setAdapter(new productList(salesOrders.getSalesOrderDetails(),getApplicationContext()));

        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent product_intent=new Intent(getApplication().getApplicationContext(),product_activity.class);
               // Customer customer=new Customer("arwa","Alzeaagi","arwazeaagi@gmail.com","0556693340","555885");
                Products products =salesOrders.getSalesOrderDetails().get(position+1).getProducts();
                product_intent.putExtra("product",products);
                startActivity(product_intent);
            }
        });*/

    }


}
