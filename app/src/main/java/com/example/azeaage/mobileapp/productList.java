package com.example.azeaage.mobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import objects.Products;


/**
 * Created by AZeaage on 4/2/2017.
 */

public class productList  extends BaseAdapter {
    ArrayList<Products> products;
    Context context;
    Products product;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.order_detalis, parent,false);
        TextView orderNumTV=(TextView)row.findViewById(R.id.orderNumberTV);
        TextView orderStatus =(TextView)row.findViewById(R.id.order_Status);
        product =products.get(position);

       // String InvoiceNo=product.()+"";
        //orderNumTV.setText(InvoiceNo);
        orderStatus.setText("In process");

        return row;
    }
}
