package com.example.azeaage.mobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.azeaage.mobileapp.R;

import java.util.ArrayList;

import objects.Products;

/**
 * Created by AZeaage on 4/18/2017.
 */

public class salesProductsAdapter extends BaseAdapter {
    ArrayList<Products> productsArrayList;
    Products products;
    Context context;

    public salesProductsAdapter(ArrayList<Products> productsArrayList, Context context) {
        this.productsArrayList = productsArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View row = inflater.inflate(R.layout.sales_products, parent,false);
        return row;
    }
}
