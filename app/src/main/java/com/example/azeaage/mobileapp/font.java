package com.example.azeaage.mobileapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by AZeaage on 5/7/2017.
 */

public class font {
    Typeface typeface;
    public void ChangeFontToBold(View view, Context c){
        typeface=Typeface.createFromAsset(c.getAssets(),"fonts/Cairo-SemiBold.ttf");
        setTypeface(view);
    }
    public void ChangeFontToLight(View view, Context c){
        typeface=Typeface.createFromAsset(c.getAssets(),"fonts/cairo-light.ttf");
        setTypeface(view);
    }

    private void setTypeface(View view) {
        if(view instanceof Button){
            Button b=(Button)view;
            b.setTypeface(typeface);
        }
        else if(view instanceof TextView){
            TextView t =(TextView)view;
            t.setTypeface(typeface);
        }
        else if(view instanceof AutoCompleteTextView){
            AutoCompleteTextView autoText =(AutoCompleteTextView)view;
            autoText.setTypeface(typeface);
        }
    }

}
