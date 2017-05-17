package com.example.azeaage.mobileapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.azeaage.mobileapp.R;
import com.example.azeaage.mobileapp.font;

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


            View row = inflater.inflate(R.layout.order, parent, false);
           // LinearLayout linearLayout = (LinearLayout) row.findViewById(R.id.linearLayout);
           /* if (position % 2 == 0) {
                linearLayout.setBackgroundColor(Color.parseColor("#BABABA"));
            }*/
            TextView orderNumTV = (TextView) row.findViewById(R.id.Location);
            TextView creationDate = (TextView) row.findViewById(R.id.order_Date);
            TextView locationTV = (TextView) row.findViewById(R.id.location_TV);
            ImageView location_image=(ImageView)row.findViewById(R.id.location);
            order = orders.get(position);
            font f =new font();
            f.ChangeFontToBold(orderNumTV,context);
            f.ChangeFontToBold(locationTV,context);
            f.ChangeFontToBold(creationDate,context);

            String InvoiceNo = order.getSAPInvoiceNo() + "";
            orderNumTV.setText(InvoiceNo);

        //date = nill ?? order.getOrderDate()
            creationDate.setText(order.getOrderDate());

            if(order.getLat().equals("")){
               location_image.setImageResource(R.drawable.ic_location_off_black_24dp);
                locationTV.setText("لم يتم");
            }
            else{
                location_image.setImageResource(R.drawable.ic_location_on_black_24dp);
                locationTV.setText("تم");
            }


        return row;
    }
    }

