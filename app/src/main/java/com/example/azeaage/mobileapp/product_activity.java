package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;

import objects.Products;

/**
 * Created by AZeaage on 4/4/2017.
 */

public class product_activity extends AppCompatActivity implements HorizontalNumberPickerListener {

    //UI
    TextView name_ar,name_en,color,size,cost,discount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);
//      HorizontalNumberPickerListener horizontalNumberPickerListener1=(HorizontalNumberPickerListener) findViewById(R.id.horizontal_number_picker1);
       // System.out.print(horizontalNumberPickerListener1.toString()+"777777777777777");

        name_ar=(TextView)findViewById(R.id.product_name_ar);
        name_en=(TextView)findViewById(R.id.product_name_en);
        color=(TextView)findViewById(R.id.product_color);
        size=(TextView)findViewById(R.id.product_size);
        cost=(TextView)findViewById(R.id.price_product);
        discount=(TextView)findViewById(R.id.price_after_discount);

    /*    Intent intent =getIntent();
        Products products=(Products) intent.getSerializableExtra("product");
        System.out.println(products);
        name_ar.setText(products.getProductName_Ar());
        name_en.setText(products.getProductName_En());
        //color.setText(products.get);
        cost.setText(products.getPrice()+"");*/
    }

    @Override
    public void onHorizontalNumberPickerChanged(HorizontalNumberPicker horizontalNumberPicker, int value) {
        System.out.println(value+"              heeeeeeeeeer");
    }
}
