package com.example.azeaage.mobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import objects.SalesOrders;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class orderList extends BaseAdapter{
    ArrayList<SalesOrders> orders;
    Context context;
    SalesOrders order;

    public orderList(ArrayList<SalesOrders> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override

    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.order, parent,false);
        TextView orderNumTV=(TextView)row.findViewById(R.id.orderNumberTV);
        TextView orderStatus =(TextView)row.findViewById(R.id.order_Status);
        order =orders.get(position);

        String InvoiceNo=order.getSAPInvoiceNo()+"";
        orderNumTV.setText(InvoiceNo);
        orderStatus.setText("In process");


        return row;
    }
    }

