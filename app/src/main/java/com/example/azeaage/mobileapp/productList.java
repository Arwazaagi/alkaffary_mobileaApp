package com.example.azeaage.mobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import objects.Products;
import objects.SalesOrderDetails;


/**
 * Created by AZeaage on 4/2/2017.
 */

public class productList  extends BaseAdapter {
    ArrayList<SalesOrderDetails> salesOrderDetails;
    Context context;
    SalesOrderDetails salesOrderDetail;
    Products products;

    public productList(ArrayList<SalesOrderDetails> salesOrderDetails, Context context) {
        this.salesOrderDetails = salesOrderDetails;
        this.context = context;
    }

    @Override
    public int getCount() {
        return salesOrderDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return salesOrderDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.product_details, parent,false);
        TextView itemNum=(TextView)row.findViewById(R.id.Item_tv);
        TextView cost =(TextView)row.findViewById(R.id.Unit_cost);
        TextView quantity =(TextView)row.findViewById(R.id.quintity_tv);
        TextView discount =(TextView)row.findViewById(R.id.discount);
        TextView total =(TextView)row.findViewById(R.id.Total);
        salesOrderDetail=salesOrderDetails.get(position);
        String quantityInt=salesOrderDetail.getQty()+"";
        quantity.setText(quantityInt);
        String costDouble=salesOrderDetail.getPrice()+"";
        cost.setText(costDouble);
       itemNum.setText(salesOrderDetail.getRecordID()+"");
        discount.setText("20%");
       total.setText(salesOrderDetail.getLineTotal()+"");
      //  product =products.get(position);



        return row;
    }
}
