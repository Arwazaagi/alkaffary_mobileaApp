package com.example.azeaage.mobileapp.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.azeaage.mobileapp.R;
import com.example.azeaage.mobileapp.font;

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
        TextView productName = (TextView)row.findViewById(R.id.productName);
        TextView priceBefor = (TextView)row.findViewById(R.id.PriceBefor);
        TextView priceAfter = (TextView)row.findViewById(R.id.PriceAfter);
        TextView priceTotal = (TextView)row.findViewById(R.id.TotalPriceTV);
        TextView totalPriceTV=(TextView)row.findViewById(R.id.TotalPrice);
        TextView qty= (TextView)row.findViewById(R.id.quitity_tv);
        TextView product_count=(TextView)row.findViewById(R.id.product_count);
        product_count.setText((position+1)+"");
        font f =new font();
        f.ChangeFontToBold(productName,context);
        f.ChangeFontToLight(priceBefor,context);
        f.ChangeFontToLight(priceAfter,context);
        f.ChangeFontToLight(priceTotal,context);
        f.ChangeFontToLight(qty,context);
        f.ChangeFontToBold(totalPriceTV,context);
        products=productsArrayList.get(position);
        String name =products.getProductName();
        System.out.println(products+"    adapter ");
        productName.setText(name);
        int priceBeforeD,priceAfterD;
        priceBeforeD=products.getPriceBeforeDisc().intValue();
        priceAfterD=products.getPriceAfterDisc().intValue();

        priceBefor.setText( priceBeforeD+"ريال سعودي للقطعة ");
        if(priceAfterD==priceBeforeD){
            priceAfter.setText("بضاعة جديدة");


        }
        else{
       priceAfter.setText(priceAfterD+"ريال سعودي ");
            priceAfter.setPaintFlags(priceAfter.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
        priceTotal.setText(products.getLineTotal()+"ريال سعودي ");
        qty.setText(products.getQty().intValue()+"قطع ");
        return row;
    }
}
